package com.clean.application.configuration.queries;

import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUnitExchangeValueQuery implements IRequest<Short> {
    int itemDetailId;
    int unitId;
    short quantity;
}
