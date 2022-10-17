package com.clean.application.itemrequest.commands;

import com.clean.application.configuration.queries.GetUnitExchangeValueQuery;
import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItemRequestDetailCommandHandler
        implements IRequestHandler<CreateItemRequestDetailCommand, ItemRequestDetailModel> {
    private ItemRequestDetailRepository itemRequestDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemRepository itemRepository;
    private IMediator mediator;
    private StageRepository stageRepository;
    private ItemRequestRepository itemRequestRepositoy;
    @Autowired
    public CreateItemRequestDetailCommandHandler(ItemRequestDetailRepository ItemRequestDetailRepository,
            IMediator mediator, ItemDetailRepository itemDetailRepository,ItemRepository itemRepository,StageRepository stageRepository,ItemRequestRepository itemRequestRepository ){
        this.itemRequestDetailRepository = ItemRequestDetailRepository;
        this.mediator = mediator;
        this.itemDetailRepository = itemDetailRepository;
        this.itemRepository = itemRepository;
        this.stageRepository = stageRepository;
        this.itemRequestRepositoy = itemRequestRepository;
    }

    @Override
    public ItemRequestDetailModel handle(CreateItemRequestDetailCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemRequestRepositoy.findById(request.getItemRequestId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }

        //BRC: check for item duplication 
        if(itemRequestDetailRepository.existsByItemDetailIdAndItemRequestIdAndIdIsNot(request.getItemDetailId(), request.getItemRequestId(), request.getId())){
            throw new RuntimeException("جنس درخواست شده قبلاً درخواست گردید!");
        }

         //BRC:check for unit of none consumable item. should be same
         Item item = itemRepository.findItemByItemDetial(request.getItemDetailId());
         if(!(item.getConsumable()) && item.getUnitId() != request.getUnitId()){
             throw new RuntimeException("واحد جنس با واحد درخواست شده یکسان نمیباشد!");
         } 

        // save or update item request detail entity
        ItemRequestDetail detail = itemRequestDetailRepository.findById(request.getId()).orElse(null);
        if (detail == null) {
            detail = new ItemRequestDetail();
            detail.setCompleted(false);
        }

        // exchange or convert units
        int quantity = mediator.send(
                new GetUnitExchangeValueQuery(request.getItemDetailId(), request.getUnitId(), request.getQuantity()));
        if (quantity == 0) {
            quantity = request.getQuantity();
        }
        detail.setBaseQuantity((short) quantity );
        detail.setRemain((short) quantity);
        detail.setItemDetailId(request.getItemDetailId());
        detail.setItemRequestId(request.getItemRequestId());
        detail.setQuantity(request.getQuantity());
        detail.setUnitId(request.getUnitId());
        detail.setDescription(request.getDescription());
        detail = itemRequestDetailRepository.save(detail);
        ItemRequestDetailModel model = ItemRequestDetailModel.map(detail);
        model.setItemDetailText(itemDetailRepository.findById(detail.getItemDetailId()).get().getDetail());
        return model;
    }
}
