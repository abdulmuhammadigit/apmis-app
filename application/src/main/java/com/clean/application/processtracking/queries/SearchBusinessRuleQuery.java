package com.clean.application.processtracking.queries;

import java.util.List;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBusinessRuleQuery implements IRequest<List<BusinessRuleModel>> {
    private String name;
}
