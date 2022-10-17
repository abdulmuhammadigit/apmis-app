package com.clean.application.report.queries;

import com.clean.application.report.models.EmployeeAllocatedItemDetailsModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetEmployeeAllocatedItemDetailsQuery implements IRequest<List<EmployeeAllocatedItemDetailsModel>> {
    private int employeeId;
}