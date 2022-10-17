package com.clean.application.item.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Attribute;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.persistence.item.AttributeRepository;
import com.clean.persistence.item.ItemAttributeValueRepository;
import com.clean.persistence.item.ItemDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateAttributeCommandHandler implements IRequestHandler<CreateAttributeCommand, Boolean> {
    private AttributeRepository repository;
    private ItemAttributeValueRepository itemAttributeValueRepository;
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    CreateAttributeCommandHandler(AttributeRepository repository , ItemAttributeValueRepository itemAttributeValueRespository , ItemDetailRepository itemDetailRepository){
        this.repository= repository;
        this.itemAttributeValueRepository = itemAttributeValueRespository;
        this.itemDetailRepository = itemDetailRepository;
    }
    @Override
    public Boolean handle(CreateAttributeCommand request) {
            //BRC:check for duplication
            if(repository.existsByNameAndCategoryIdAndIdIsNot(request.getName(),request.getCategoryId(),request.getId())){
                throw new RuntimeException("مشخصه برای کتگوری انتخاب شده موجود میباشد!");
            }

            Attribute attribute = repository.findById(request.getId()).orElse(null);
            if(attribute == null){
                attribute = new Attribute();
            }
            String oldName=attribute.getName();
            attribute.setName(request.getName());
            attribute.setDataTypeId(request.getDataTypeId());
            attribute.setCategoryId(request.getCategoryId());
            attribute = repository.save(attribute);
            String newName=attribute.getName();
            if(oldName != null && !oldName.equals(newName)){
                List<Integer> itemDetailList= itemAttributeValueRepository.findAllByAttributeId(attribute.getId()).stream().map(e->e.getItemDetailId()).distinct().collect(Collectors.toList());
                itemDetailList.forEach(e->{
                    ItemDetail itemDetail= itemDetailRepository.findById(e).get();
                    String  detail = itemDetail.getDetail();
                    itemDetail.setDetail(detail.replace(oldName, newName));
                    itemDetailRepository.save(itemDetail);
                });
            }
            return true;
    }
}
