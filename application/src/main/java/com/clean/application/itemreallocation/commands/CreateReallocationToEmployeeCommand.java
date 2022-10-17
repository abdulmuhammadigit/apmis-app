package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationBoardModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreateReallocationToEmployeeCommand implements IRequest<ItemReallocationBoardModel> {
    private long id;
    private long itemReallocationDetailId;
    private long itemDistributedSpecificationId;
    private String examinerBoard;
    private int statusId;
    private float price;
    private Integer toEmployeeId;
}
