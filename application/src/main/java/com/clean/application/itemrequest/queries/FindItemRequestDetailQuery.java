package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class FindItemRequestDetailQuery implements IRequest<List<ItemRequestDetailModel>> {
    private long itemRequestId;
    private boolean notDistributed;
}
