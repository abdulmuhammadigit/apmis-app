package com.clean.application.hr.commands;

import com.clean.application.hr.models.StockKeeperModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.hr.StockKeeper;
import lombok.Data;

@Data
public class CreateStockKeeperCommand implements IRequest<StockKeeperModel> {
    private short id;
    private int employeeId;
    private short stockKeeperTypeId;
    private short itemCategoryId;
    private int locationId;
    private Long userId;
}
