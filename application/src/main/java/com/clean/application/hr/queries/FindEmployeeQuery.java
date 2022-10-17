package com.clean.application.hr.queries;

import com.clean.application.hr.models.EmployeeModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindEmployeeQuery implements IRequest<EmployeeModel> {
    private long id;
}
