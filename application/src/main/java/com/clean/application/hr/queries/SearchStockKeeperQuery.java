package com.clean.application.hr.queries;

import com.clean.application.hr.models.StockKeeperModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchStockKeeperQuery implements IRequest<List<StockKeeperModel>> {
    private short stockKeeperTypeId;
}
