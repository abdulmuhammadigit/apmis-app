package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.User;
import com.clean.domain.entity.itm.Attribute;
import com.clean.persistence.item.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAttributeQueryHandler implements IRequestHandler<GetAttributeQuery, List<AttributeModel>> {
    private AttributeRepository repository;
    @Autowired
    GetAttributeQueryHandler(AttributeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<AttributeModel> handle(GetAttributeQuery request) {
        Specification specification = null;
        if(request.getCategoryId() != 0){
            specification = Specification.where((Specification<Attribute>)(root,query,critiriaBuilder)->critiriaBuilder.equal(root.get("categoryId"),(short) request.getCategoryId()));
        }
        List<Attribute> list= specification == null ? repository.findAll():repository.findAll(specification);
        return list.stream().map(e->{
            AttributeModel model = new AttributeModel();
            model.setId(e.getId());
            model.setName(e.getName());
            return model;
        }).collect(Collectors.toList());
    }
}
