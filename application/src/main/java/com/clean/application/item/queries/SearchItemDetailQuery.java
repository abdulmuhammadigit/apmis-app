package com.clean.application.item.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;
@Data
public class SearchItemDetailQuery implements IRequest<List<ItemDetailModel>> {
    private short categoryId;
    private int itemId;
    private String detail;
}
