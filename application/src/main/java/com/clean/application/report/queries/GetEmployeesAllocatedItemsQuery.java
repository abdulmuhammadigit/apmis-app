package com.clean.application.report.queries;

import com.clean.application.report.models.EmployeesAllocatedItemsModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetEmployeesAllocatedItemsQuery  implements IRequest<List<EmployeesAllocatedItemsModel>> {
    private int employeeId;
}
