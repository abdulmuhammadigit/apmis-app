package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.BusinessRule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRuleModel {
    private int id;
    private String name;
    private int entityId;
    private String descriptoin;
    private String entityText;

    public static BusinessRuleModel map(BusinessRule businessRule){
        BusinessRuleModel model = new BusinessRuleModel();
        model.setId( businessRule.getId());
        model.setName(businessRule.getName());
        model.setEntityId(businessRule.getEntityId());
        model.setDescriptoin(businessRule.getDescription());
        return model;
    }
}
