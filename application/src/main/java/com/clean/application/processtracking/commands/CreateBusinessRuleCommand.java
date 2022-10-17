package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusinessRuleCommand implements IRequest<BusinessRuleModel> {
    private int id;
    private String name;
    private int entityId;
    private String descriptoin;
}
