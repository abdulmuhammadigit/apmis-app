package com.clean.application.configuration.queries;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUnitExchangeQuery implements IRequest<UnitExchangeModel> {
    private int id;
}
