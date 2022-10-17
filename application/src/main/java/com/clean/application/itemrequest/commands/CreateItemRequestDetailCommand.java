package com.clean.application.itemrequest.commands;

import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemRequestDetailCommand implements IRequest<ItemRequestDetailModel> {
    private long id;
    private int itemDetailId;
    private long itemRequestId;
    private short quantity;
    private short remain;
    private short unitId;
    private String description;
}
