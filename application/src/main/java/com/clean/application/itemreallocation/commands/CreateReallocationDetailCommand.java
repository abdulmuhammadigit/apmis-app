package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReallocationDetailCommand implements IRequest<ItemReallocationDetailModel> {
    private long id;
    private long itemReallocationId;
    private long itemDistributionDetailId;
    private short quantity;
    private short remain;
    private short reallocationQuantity;
    private Short statusId;
    private String description;
}
