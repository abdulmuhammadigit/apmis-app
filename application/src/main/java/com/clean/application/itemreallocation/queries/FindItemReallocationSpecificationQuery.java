package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationSpecificationModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class FindItemReallocationSpecificationQuery implements IRequest<List<ItemReallocationSpecificationModel>> {
 private long itemReallocationId;
}
