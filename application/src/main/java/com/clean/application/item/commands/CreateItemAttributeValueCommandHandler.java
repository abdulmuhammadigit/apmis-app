package com.clean.application.item.commands;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.application.item.queries.SearchItemAttributeValueQuery;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemAttributeValue;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.persistence.item.ItemAttributeValueRepository;
import com.clean.persistence.item.ItemDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class CreateItemAttributeValueCommandHandler
        implements IRequestHandler<CreateItemAttributeValueCommand, List<ItemDetailModel>> {
    private ItemAttributeValueRepository itemAttributeValueRepository;
    private ItemDetailRepository itemDetailRepository;
    private IMediator mediator;

    @Autowired
    CreateItemAttributeValueCommandHandler(ItemAttributeValueRepository itemAttributeValueRepository,
            ItemDetailRepository itemDetailRepository, IMediator mediator) {
        this.itemAttributeValueRepository = itemAttributeValueRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.mediator = mediator;
    }

    @Override
    public List<ItemDetailModel> handle(CreateItemAttributeValueCommand request) {
    
        ItemDetail itemDetail = itemDetailRepository.findById(request.getItemDetailId()).orElse(null);
        boolean newItemDetail = false;
        if (itemDetail == null) {
            itemDetail = new ItemDetail();
            newItemDetail = true;
        }
        itemDetail.setItemId(request.getItemId());
        itemDetail.setDetail(request.getDetail());

        itemDetail = itemDetailRepository.save(itemDetail);
        int itemDetailId = itemDetail.getId();

        if (newItemDetail) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("IM" + "0000000");
            String strSequence = Integer.toString(itemDetailId);
            int lenght = strSequence.length();
            String result = stringBuilder.toString().replaceFirst(".{" + lenght + "}$", strSequence);
            itemDetail.setCode(result);
            itemDetailRepository.save(itemDetail);
        }

        List<ItemAttributeValue> itemAttributeValueList = new ArrayList<>();
        request.getItemAttributeValueList().forEach(value -> {
            ItemAttributeValue itemAttributeValue = itemAttributeValueRepository.findById(value.getId()).orElse(null);
            if (itemAttributeValue == null) {
                itemAttributeValue = new ItemAttributeValue();
            }
            itemAttributeValue.setAttributeId(value.getAttributeId());
            itemAttributeValue.setItemDetailId(itemDetailId);
            itemAttributeValue.setUnitId(value.getUnitId());
            itemAttributeValue.setValue(value.getValue());
            itemAttributeValueList.add(itemAttributeValue);
        });
        itemAttributeValueRepository.saveAll(itemAttributeValueList);

        return mediator.send(new SearchItemAttributeValueQuery(itemDetailId));
    }
}
