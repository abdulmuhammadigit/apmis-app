package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;
import java.util.List;

@Data
public class SearchItemReallocationQuery implements IRequest<List<ItemReallocationSearchModel>> {
    private String code;
    private String documentNumber;
}
