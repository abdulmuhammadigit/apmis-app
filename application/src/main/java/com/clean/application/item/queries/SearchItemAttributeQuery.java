package com.clean.application.item.queries;

import com.clean.application.item.models.ItemAttributeModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchItemAttributeQuery implements IRequest<List<ItemAttributeModel>> {
    private  int itemId;
}
