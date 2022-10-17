package com.clean.application.auction.quries;

import com.clean.application.itemreallocation.models.ItemReallocationSpecificationSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchReallocatedItemQuery implements IRequest<List<ItemReallocationSpecificationSearchModel>> {
    private String tagNumber;
    private String serialNumber;
    private String itemDetail;
}
