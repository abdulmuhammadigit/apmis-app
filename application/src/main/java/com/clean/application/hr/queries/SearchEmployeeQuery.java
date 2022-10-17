package com.clean.application.hr.queries;

import com.clean.application.hr.models.EmployeeModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.hr.Employee;
import lombok.Data;

import java.util.List;
@Data
public class SearchEmployeeQuery implements IRequest<List<EmployeeModel>> {
    private String code;
    private String name;
    private String lastName;
    private String fatherName;
}
