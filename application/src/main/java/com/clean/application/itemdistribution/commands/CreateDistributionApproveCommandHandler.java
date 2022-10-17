package com.clean.application.itemdistribution.commands;

import com.clean.application.services.UserService;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.stc.ItemDistributed;
import com.clean.domain.entity.stc.ItemDistributedSpecification;
import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.domain.entity.stc.ItemSpecification;
import com.clean.persistence.hr.StockKeeperRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemdistribution.ItemDistributedRepository;
import com.clean.persistence.itemdistribution.ItemDistributedSpecificationRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class CreateDistributionApproveCommandHandler
        implements IRequestHandler<CreateDistributionApproveCommand, Boolean> {

    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private ItemDistributedRepository itemDistributedRepository;
    private ItemDistributedSpecificationRepository itemDistributedSpecificationRepository;
    private ItemRequestDetailRepository itemRequestDetailRepository;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    private ItemRepository itemRepository;
    private ItemSpecificationRepository itemSpecificationRepository;
    private StockKeeperRepository stockKeeperRepository;
    private UserService userService;

    @Autowired
    public CreateDistributionApproveCommandHandler(ItemDistributionDetailRepository itemDistributionDetailRepository,
            ItemDistributedRepository itemDistributedRepository,
            ItemDistributedSpecificationRepository itemDistributedSpecificationRepository,
            ItemRequestDetailRepository itemRequestDetailRepository,
            ItemReceiptDetailRepository itemReceiptDetailRepository, ItemRepository itemRepository,
            ItemSpecificationRepository itemSpecificationRepository,
            StockKeeperRepository stockKeeperRepository,
            UserService userService){
        this.itemDistributionDetailRepository = itemDistributionDetailRepository;
        this.itemDistributedRepository = itemDistributedRepository;
        this.itemDistributedSpecificationRepository = itemDistributedSpecificationRepository;
        this.itemRequestDetailRepository = itemRequestDetailRepository;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
        this.itemRepository = itemRepository;
        this.itemSpecificationRepository = itemSpecificationRepository;
        this.stockKeeperRepository = stockKeeperRepository;
        this.userService = userService;
    }

    @Override
    public Boolean handle(CreateDistributionApproveCommand request) {
        // 0. check for data
        if(!itemDistributionDetailRepository.existsByItemDistributionId(request.getItemDistributionId())){
            throw new RuntimeException("اجناس برای تائید دریافت نگردید!");
        }

        // 1. get all distributed list
        List<ItemDistributionDetail> itemDistributionDetailList = itemDistributionDetailRepository
                .findAllByItemDistributionIdAndDistributed(request.getItemDistributionId(), false);
        if (itemDistributionDetailList != null && itemDistributionDetailList.size() > 0) {
            // 2. loop according to distributed list
            for (ItemDistributionDetail distributionDetail : itemDistributionDetailList) {
                // 3. find request detail
                ItemRequestDetail itemRequestDetail = itemRequestDetailRepository
                        .findById(distributionDetail.getItemRequestDetailId()).get();
                short requestedQuantity = itemRequestDetail.getRemain();
                short distributedQuantity = distributionDetail.getQuantity();
                short requestedRemainQuantity = 0;
                // 3.1 check for complation of request and distributed quantity
                if (distributedQuantity == requestedQuantity) {
                    itemRequestDetail.setCompleted(true);
                    itemRequestDetail.setStatusId(StatusMapped.REQUEST_DETAIL_COMPLETED_DISTRIBUTION);
                } else {
                    requestedRemainQuantity = (short) (requestedQuantity - distributedQuantity);
                    itemRequestDetail.setStatusId(StatusMapped.REQUEST_DETAIL_PARTIAL_DISTRIBUTION);
                }
                // 3.2 check distributed should not be greater than requested quantity
                if (distributedQuantity > requestedQuantity) {
                    throw new RuntimeException("مقدار توزیع نمیتواند که بزرگتر از مقدار درخواست باشد!");
                }

                itemRequestDetail.setRemain(requestedRemainQuantity);
                itemRequestDetailRepository.save(itemRequestDetail);

                // 4. check for consumable
                Item item = itemRepository.findItemByItemDetial(distributionDetail.getItemDetailId());
                boolean consumable = item.getConsumable();
                // 5. get Receipt detail according to stock keeper

                Long userId=userService.getUserId();
                StockKeeper stockKeeper = stockKeeperRepository.findByUserId(userId);
                if(stockKeeper == null){
                    throw new RuntimeException("معتمد دریافت نگردید!");
                }
                List<ItemReceiptDetail> itemReceiptDetails = itemReceiptDetailRepository
                        .findByStockKeeperAndItemDetail(stockKeeper.getId(), distributionDetail.getItemDetailId(), false,StatusMapped.RECEIPT_USABLE);

                if ((itemReceiptDetails.stream().mapToInt(e -> e.getRemain()).sum()) < distributedQuantity) {
                    throw new RuntimeException("شما مقدار کافی برای توزیع جنس مذکور ندارید!");
                }
                // 5.1 distribute item according to receipt list
                for (ItemReceiptDetail detail : itemReceiptDetails) {
                    // 6. save distributed items
                    ItemDistributed itemDistributed = new ItemDistributed();
                    itemDistributed.setItemDistributionDetailId(distributionDetail.getId());
                    itemDistributed.setItemReceiptDetailId(detail.getId());
                    // 5.2 find receipt quantity according : remain quantity
                    short receiptQuantity = detail.getRemain();
                    short quantity = 0;
                    short remainQuantity = detail.getRemain();
                    if (receiptQuantity >= distributedQuantity) {
                        quantity = distributedQuantity;
                        remainQuantity = (short) (receiptQuantity - distributedQuantity);
                        distributedQuantity = 0;
                    } else {
                        quantity = receiptQuantity;
                        distributedQuantity = (short) (distributedQuantity - receiptQuantity);
                        remainQuantity = 0;
                    }
                    itemDistributed.setQuantity(quantity);

                    itemDistributed = itemDistributedRepository.save(itemDistributed);
                    if (remainQuantity == 0) {
                        detail.setFinished(true);
                    }
                    detail.setRemain(remainQuantity);
                    itemReceiptDetailRepository.save(detail);
                    // 7. check for consumable
                    if (consumable == false) {
                        // 7.1 get all item specifications
                        List<ItemSpecification> itemSpecifications = itemSpecificationRepository
                                .findAllByItemReceiptDetailIdAndStatusId(detail.getId(), StatusMapped.SPECIFICATION_WAREHOUSE);
                        if (itemSpecifications.size() < quantity) {
                            throw new RuntimeException("تعداد درخواست شده و اجناس موجوده در راپور رسید یکسان نمیباشد!");
                        }
                        for (ItemSpecification specification : itemSpecifications) {
                            if (quantity == 0) {
                                break;
                            }
                            // 8. save distributed specification
                            ItemDistributedSpecification itemDistributedSpecification = new ItemDistributedSpecification();

                            itemDistributedSpecification.setItemDistributedId(itemDistributed.getId());
                            itemDistributedSpecification.setItemSpecificationId(specification.getId());
                            itemDistributedSpecification.setStatusId(StatusMapped.DISTRIBUTION_SPECIFICATION_USE);
                            itemDistributedSpecificationRepository.saveAndFlush(itemDistributedSpecification);

                            specification.setStatusId(StatusMapped.SPECIFICATION_DISTRIBUTED);
                            itemSpecificationRepository.save(specification);
                            quantity = (short) (quantity - 1);
                        }
                    }

                    // 9.do not continue because all item are distributed
                    if (distributedQuantity == 0) {
                        break;
                    }
                }

                distributionDetail.setDistributed(true);
                distributionDetail.setStatusId(StatusMapped.DISTRIBUTION_DETAIL_DISTRIBUTED);
                itemDistributionDetailRepository.save(distributionDetail);
            }
        } else { 
            throw new RuntimeException("اجناس تائید گردیده اند!");
        }
        return true;
    }
}
