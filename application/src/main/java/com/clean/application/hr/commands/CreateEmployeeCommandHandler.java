package com.clean.application.hr.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.persistence.hr.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateEmployeeCommandHandler implements IRequestHandler<CreateEmployeeCommand, Employee> {
   private EmployeeRepository employeeRepository;
   @Autowired
    CreateEmployeeCommandHandler(EmployeeRepository employeeRepository){
       this.employeeRepository = employeeRepository;
   }
    @Override
    public Employee handle(CreateEmployeeCommand request) {
       Employee employee = employeeRepository.findById(request.getId()).orElse(null);
       if(employee ==null){
           employee = new Employee();
           employee.setCode(request.getCode());
       }
       employee.setName(request.getName());
       employee.setLastName(request.getLastName());
       employee.setFatherName(request.getFatherName());
       employee.setGrandFatherName(request.getGrandFatherName());
       employee.setPosition(request.getPosition());
       employee.setOrgUnitId(request.getOrgUnitId());
       employee.setCardId(request.getCardId());
       return  employeeRepository.save(employee);
    }
}
