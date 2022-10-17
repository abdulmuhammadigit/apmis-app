package com.clean.application.itemdistribution.commands;

import com.clean.application.itemdistribution.models.ItemDistributionDetailModel;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import com.clean.persistence.look.UnitRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItemDistributionDetailCommandHandler
        implements IRequestHandler<CreateItemDistributionDetailCommand, ItemDistributionDetailModel> {
    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemRequestDetailRepository itemRequestDetailRepository;
    private UnitRepository UnitRepository;
    private StageRepository stageRepository;
    private ItemDistributionRepository itemDistributionRepository;
    @Autowired
    public CreateItemDistributionDetailCommandHandler(ItemDistributionDetailRepository detailRepository,
            ItemDetailRepository itemDetailRepository, ItemRequestDetailRepository itemRequestDetailRepository,
            UnitRepository unitRepository,
            StageRepository stageRepository,
            ItemDistributionRepository itemDistributionRepository) {
        this.itemDistributionDetailRepository = detailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.itemRequestDetailRepository = itemRequestDetailRepository;
        this.UnitRepository = unitRepository;
        this.stageRepository = stageRepository;
        this.itemDistributionRepository = itemDistributionRepository;
    }

    @Override
    public ItemDistributionDetailModel handle(CreateItemDistributionDetailCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemDistributionRepository.findById(request.getItemDistributionId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }

        //BRC:1 check for item detail duplication
        if(itemDistributionDetailRepository.existsByItemDistributionIdAndItemDetailIdAndIdIsNot(request.getItemDistributionId(), request.getItemDetailId(), request.getId())){
            throw new RuntimeException("جنس انتخاب شده قبلاً برای توزیع انتخاب گردیده است!");
        }

        //BRC:1 check for item detail duplication
        if(request.getQuantity() > request.getRemain()){
            throw new RuntimeException("مقدار توزیع اجناس نمیتواند بزگتر از مقدار باقیمانده باشد!");
        }

        // save or update item distribution detail entity
        ItemDistributionDetail itemDistributionDetail = itemDistributionDetailRepository.findById(request.getId())
                .orElse(null);

        if (itemDistributionDetail == null) {
            itemDistributionDetail = new ItemDistributionDetail();
            itemDistributionDetail.setDistributed(false);
            itemDistributionDetail.setStatusId(StatusMapped.DISTRIBUTION_DETAIL_UNDER_DISTRIBUTION);
            itemDistributionDetail.setReallocatedQuantity((short)0);
        }

        if (itemDistributionDetail.isDistributed()) {
            throw new RuntimeException("شما نمیتوانید جنس توزیع شده را ویرایش نمائید!");
        }

        itemDistributionDetail.setItemDistributionId(request.getItemDistributionId());
        itemDistributionDetail.setItemRequestDetailId(request.getItemRequestDetailId());
        itemDistributionDetail.setItemDetailId(request.getItemDetailId());
        itemDistributionDetail.setQuantity(request.getQuantity());
        itemDistributionDetail = itemDistributionDetailRepository.save(itemDistributionDetail);

        // map data
        ItemDistributionDetailModel detailModel = ItemDistributionDetailModel.map(itemDistributionDetail);
        ItemDetail itemDetail = itemDetailRepository.findById(itemDistributionDetail.getItemDetailId()).get();
        detailModel.setItemDetailText(itemDetail.getDetail());
        detailModel.setUnitText(UnitRepository.findByItemId(itemDetail.getItemId()).getName());
        ItemRequestDetail requestDetail = itemRequestDetailRepository
                .findById(itemDistributionDetail.getItemRequestDetailId()).get();
        detailModel.setRemain(requestDetail.getRemain());
        detailModel.setRequestedQuantity(requestDetail.getBaseQuantity());
        return detailModel;
    }

}
