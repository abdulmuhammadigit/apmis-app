package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationBoardModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreatePricingBoardCommand implements IRequest<ItemReallocationBoardModel> {
    private long id;
    private long itemReallocationDetailId;
    private long price;
}
