package com.clean.application.hr.commands;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.hr.Employee;
import lombok.Data;

@Data
public class CreateEmployeeCommand implements IRequest<Employee> {
    private int id;
    private String code;
    private String name;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private String position;
    private int orgUnitId;
    private String cardId;
}
