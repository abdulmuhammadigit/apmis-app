package com.clean.application.look.quries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.AttributeDataType;
import com.clean.persistence.look.AttributeDataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAttributeDataTypeQueryHandler implements IRequestHandler<SearchAttributeDataTypeQuery, List<AttributeDataType>> {
    private AttributeDataTypeRepository repository;
    @Autowired
    SearchAttributeDataTypeQueryHandler(AttributeDataTypeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<AttributeDataType> handle(SearchAttributeDataTypeQuery request) {
        return repository.findAll();
    }
}
