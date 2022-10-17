package com.clean.application.processtracking.queries;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindBusinessRuleQuery implements IRequest<BusinessRuleModel> {
    private int id;
}
