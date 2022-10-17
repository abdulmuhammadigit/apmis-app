package com.clean.application.configuration.queries;

import java.util.List;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUnitExchangeQuery implements IRequest<List<UnitExchangeModel>> {
    int toUnitId;
}
