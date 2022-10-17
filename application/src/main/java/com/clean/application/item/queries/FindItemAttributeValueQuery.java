package com.clean.application.item.queries;

import com.clean.application.item.models.ItemAttributeValueModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;
@Data
public class FindItemAttributeValueQuery implements IRequest<List<ItemAttributeValueModel>> {
    private int itemDetailId;
    private int itemId;
}
