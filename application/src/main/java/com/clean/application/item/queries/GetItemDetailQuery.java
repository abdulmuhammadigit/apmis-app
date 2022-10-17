package com.clean.application.item.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetItemDetailQuery implements IRequest<List<ItemDetailModel>> {
    private int itemId;
}
