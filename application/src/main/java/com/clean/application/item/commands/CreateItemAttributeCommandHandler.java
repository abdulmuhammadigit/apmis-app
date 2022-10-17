package com.clean.application.item.commands;

import com.clean.application.item.models.ItemAttributeModel;
import com.clean.application.item.queries.SearchItemAttributeQuery;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemAttribute;
import com.clean.persistence.item.ItemAttributeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateItemAttributeCommandHandler implements IRequestHandler<CreateItemAttributeCommand,List<ItemAttributeModel>> {
    private ItemAttributeRespository respository;
    private IMediator mediator;
    @Autowired
    CreateItemAttributeCommandHandler(ItemAttributeRespository respository,IMediator mediator){
        this.respository = respository;
        this.mediator = mediator;
    }
    @Override
    public List<ItemAttributeModel> handle(CreateItemAttributeCommand request) {
        List<ItemAttribute> attributeList = new ArrayList<>();
        request.getAttributeList().forEach(attributeId->{
            if(respository.existsByItemIdAndAttributeId(request.getItemId(),attributeId)){
                throw new RuntimeException("برای جنس مذکور مشخصه تکرار انتخاب گردیده است!");
            }

            ItemAttribute itemAttribute = new ItemAttribute();
            itemAttribute.setItemId(request.getItemId());
            itemAttribute.setAttributeId(attributeId);
            attributeList.add(itemAttribute);
        });
        respository.saveAll(attributeList);
        return mediator.send(new SearchItemAttributeQuery(request.getItemId()));
    }
}
