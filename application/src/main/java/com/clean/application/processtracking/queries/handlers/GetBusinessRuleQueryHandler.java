package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.BusinessRuleRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetBusinessRuleQueryHandler implements IRequestHandler<GetBusinessRuleQuery,List<BusinessRuleModel>> {
    private BusinessRuleRepository businessRuleRepository;
    @Autowired
    public GetBusinessRuleQueryHandler(BusinessRuleRepository businessRuleRepository) {
        this.businessRuleRepository = businessRuleRepository;
    }
    @Override
    public List<BusinessRuleModel> handle(GetBusinessRuleQuery request) {
        return businessRuleRepository.findAll().stream().map(map->{
            BusinessRuleModel model = new BusinessRuleModel();
            model.setId(map.getId());
            model.setName(map.getName());
            return model;
        }).collect(Collectors.toList());
    }
    
}
