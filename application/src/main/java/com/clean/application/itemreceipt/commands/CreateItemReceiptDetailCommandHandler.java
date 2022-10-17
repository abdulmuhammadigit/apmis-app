package com.clean.application.itemreceipt.commands;

import com.clean.application.configuration.queries.GetUnitExchangeValueQuery;
import com.clean.application.itemreceipt.models.ItemReceiptDetailModel;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItemReceiptDetailCommandHandler implements IRequestHandler<CreateItemReceiptDetailCommand,ItemReceiptDetailModel> {
    private ItemReceiptDetailRepository repositoryDetail;
    private IMediator mediator;
    private ItemRepository itemRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemSpecificationRepository itemSpecificationRepository;
    private StageRepository stageRepository;
    private ItemReceiptRepository itemReceiptRepository;
    @Autowired
    public CreateItemReceiptDetailCommandHandler(ItemReceiptDetailRepository repositoryDetail, IMediator mediator,
    ItemRepository itemRepository,ItemDetailRepository itemDetailRepository, ItemSpecificationRepository itemSpecificationRepository, StageRepository stageRepository,ItemReceiptRepository itemReceiptRepository) {
        this.repositoryDetail = repositoryDetail;
        this.mediator = mediator;
        this.itemRepository = itemRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.itemSpecificationRepository = itemSpecificationRepository;
        this.stageRepository = stageRepository;
        this.itemReceiptRepository = itemReceiptRepository;
    }
    @Override
    public ItemReceiptDetailModel handle(CreateItemReceiptDetailCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemReceiptRepository.findById(request.getItemReceiptId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }

        //BRC:check for item duplication
        if(repositoryDetail.existsByItemReceiptIdAndItemDetailIdAndIdIsNot(request.getItemReceiptId(), request.getItemDetailId(), request.getId())){
            throw new RuntimeException("جنس درخواست شده قبلاً درج گردیده است!");
        }

        //BRC:check for specification count
        if( itemSpecificationRepository.countByItemReceiptDetailId(request.getId()) > request.getQuantity()){
            throw new RuntimeException("تعداد اجناس انتخاب شده و جزئیات آن با هم تفاوت دارند");
        }

                 //BRC:check for unit of none consumable item. should be same
                 Item item = itemRepository.findItemByItemDetial(request.getItemDetailId());
                 if(!(item.getConsumable()) && item.getUnitId() != request.getUnitId()){
                     throw new RuntimeException("واحد جنس با واحد درخواست شده یکسان نمیباشد!");
                 } 
        

       // save or update item receipt detail
       ItemReceiptDetail detail = repositoryDetail.findById(request.getId()).orElse(null);
       if (detail == null) {
           detail = new ItemReceiptDetail();
           detail.setFinished(false);
       }

       // exchange or convert units
       int quantity = mediator.send(
               new GetUnitExchangeValueQuery(request.getItemDetailId(), request.getUnitId(), request.getQuantity()));
       if (quantity == 0) {
           quantity = request.getQuantity();
       }
       detail.setBaseQuantity((short) quantity);
       detail.setRemain((short) quantity);
       detail.setItemReceiptId(request.getItemReceiptId());
       detail.setItemDetailId(request.getItemDetailId());
       detail.setUnitId(request.getUnitId());
       detail.setQuantity(request.getQuantity());
       detail.setPrice(request.getPrice());
       detail.setDescription(request.getDescription());
       detail.setLocalProduct(request.isLocalProduction());
       detail = repositoryDetail.save(detail);

       ItemReceiptDetailModel model = ItemReceiptDetailModel.map(detail);
       model.setItemDetailText(itemDetailRepository.findById(request.getItemDetailId()).get().getDetail());
       model.setConsumable(itemRepository.findItemByItemDetial(request.getItemDetailId()).getConsumable());
       return model;
    }
    
}
