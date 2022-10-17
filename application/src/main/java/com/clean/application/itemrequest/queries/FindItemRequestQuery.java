package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequestModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindItemRequestQuery implements IRequest<ItemRequestModel> {
    private  long id;
}
