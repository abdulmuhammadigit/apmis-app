package com.clean.application.report.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.application.report.models.ItemQuantityModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetItemQuantityQuery  implements IRequest<List<ItemQuantityModel>> {
    private short categoryId;
    private int itemId;
    private int detailId;
}
