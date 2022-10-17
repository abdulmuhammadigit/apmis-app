package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateBusinessRuleCommand;
import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.BusinessRule;
import com.clean.persistence.processtracking.BusinessRuleRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateBusinessRuleCommandHandler implements IRequestHandler<CreateBusinessRuleCommand,BusinessRuleModel>{
    private BusinessRuleRepository businessRuleRepository;
    @Autowired
    public CreateBusinessRuleCommandHandler(BusinessRuleRepository businessRuleRepository){
        this.businessRuleRepository = businessRuleRepository;
    }

    @Override
    public BusinessRuleModel handle(CreateBusinessRuleCommand request) {
        BusinessRule businessRule = businessRuleRepository.findById(request.getId()).orElse(null);
        if(businessRule == null){
            businessRule = new BusinessRule();
        }
        businessRule.setName(request.getName());
        businessRule.setDescription(request.getDescriptoin());
        businessRule.setEntityId(request.getEntityId());
        businessRule = businessRuleRepository.save(businessRule);

        return BusinessRuleModel.map(businessRule);
    }

}
