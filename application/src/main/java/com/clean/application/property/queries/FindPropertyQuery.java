package com.clean.application.property.queries;

import com.clean.application.property.models.PropertyModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;
@Data
public class FindPropertyQuery implements IRequest<List<PropertyModel>> {
    private  int employeeId;
    private long itemDistributedSpecificationId;
}
