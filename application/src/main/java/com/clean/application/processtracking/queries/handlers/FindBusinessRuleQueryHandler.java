package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.BusinessRule;
import com.clean.persistence.processtracking.BusinessRuleRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindBusinessRuleQueryHandler implements IRequestHandler<FindBusinessRuleQuery,BusinessRuleModel>{
    private BusinessRuleRepository businessRuleRepository;
    @Autowired
    public FindBusinessRuleQueryHandler(BusinessRuleRepository businessRuleRepository) {
        this.businessRuleRepository = businessRuleRepository;
    }
    @Override
    public BusinessRuleModel handle(FindBusinessRuleQuery request) {
        BusinessRule businessRule= businessRuleRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Not Found!"));
        return BusinessRuleModel.map(businessRule);
    }
    
}
