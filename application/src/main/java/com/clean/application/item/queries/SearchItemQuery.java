package com.clean.application.item.queries;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchItemQuery implements IRequest<List<ItemModel>> {
    private short categoryId;
    private String name;
}
