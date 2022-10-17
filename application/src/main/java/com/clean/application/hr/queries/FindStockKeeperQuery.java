package com.clean.application.hr.queries;

import com.clean.application.hr.models.StockKeeperModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindStockKeeperQuery implements IRequest<StockKeeperModel> {
    private short id;
}
