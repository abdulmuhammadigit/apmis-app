package com.clean.application.configuration.commands;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUnitExhcangeCommand implements IRequest<UnitExchangeModel> {
    private int id;
    private int unitId;
    private short quantity;
    private int toUnitId;
    private Integer itemId;
}
