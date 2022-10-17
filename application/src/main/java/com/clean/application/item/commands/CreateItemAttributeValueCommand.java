package com.clean.application.item.commands;

import com.clean.application.item.models.ItemAttributeValueModel;
import com.clean.application.item.models.ItemAttributeValueRequestModel;
import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateItemAttributeValueCommand implements IRequest<List<ItemDetailModel>> {
    private int itemDetailId;
    private int itemId;
    private String detail;
    private List<ItemAttributeValueRequestModel> itemAttributeValueList;
}
