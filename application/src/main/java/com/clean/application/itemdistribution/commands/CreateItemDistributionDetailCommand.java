package com.clean.application.itemdistribution.commands;

import com.clean.application.itemdistribution.models.ItemDistributionDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDistributionDetailCommand implements IRequest<ItemDistributionDetailModel> {
    private long id;
    private long itemDistributionId;
    private long itemRequestDetailId;
    private int itemDetailId;
    private short quantity;
    private short remain;
}
