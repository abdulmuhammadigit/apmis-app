package com.clean.application.item.queries;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindItemQuery implements IRequest<ItemModel> {
    private int id;
}
