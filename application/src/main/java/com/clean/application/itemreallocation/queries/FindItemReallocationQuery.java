package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindItemReallocationQuery implements IRequest<ItemReallocationModel> {
    private long id;
}
