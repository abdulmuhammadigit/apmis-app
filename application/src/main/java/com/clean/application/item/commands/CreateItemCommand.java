package com.clean.application.item.commands;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreateItemCommand implements IRequest<ItemModel> {
    private int id;
    private short categoryId;
    private boolean consumable;
    private String name;
    private short unitId;
}
