package com.clean.application.item.commands;

import com.clean.application.item.models.ItemAttributeModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateItemAttributeCommand implements IRequest<List<ItemAttributeModel>> {
    private int itemId;
    private List<Integer> attributeList;
}
