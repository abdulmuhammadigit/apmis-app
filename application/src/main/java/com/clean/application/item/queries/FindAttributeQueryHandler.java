package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Attribute;
import com.clean.persistence.item.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FindAttributeQueryHandler implements IRequestHandler<FindAttributeQuery, AttributeModel> {
    private AttributeRepository repository;
    @Autowired
    FindAttributeQueryHandler(AttributeRepository repository){
        this.repository = repository;
    }
    @Override
    public AttributeModel handle(FindAttributeQuery request) {
        return repository.findById(request.getId()).map(AttributeModel::map).orElseThrow(()->new RuntimeException("Not Found"));
    }
}
