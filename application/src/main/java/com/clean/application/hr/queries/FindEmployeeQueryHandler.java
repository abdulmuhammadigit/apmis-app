package com.clean.application.hr.queries;

import com.clean.application.hr.models.EmployeeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.hr.OrgUnit;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.hr.OrgUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FindEmployeeQueryHandler implements IRequestHandler<FindEmployeeQuery,EmployeeModel> {
   private EmployeeRepository employeeRepository;
   private OrgUnitRepository orgUnitRepository;
   @Autowired
    FindEmployeeQueryHandler(EmployeeRepository employeeRepository, OrgUnitRepository orgUnitRepository){
       this.employeeRepository = employeeRepository;
       this.orgUnitRepository = orgUnitRepository;
   }
    @Override
    public EmployeeModel handle(FindEmployeeQuery request) {
        Employee employee = employeeRepository.findById((int) request.getId()).orElseThrow(()->new RuntimeException("کارمند دریافت نگردید!"));
        OrgUnit orgUnit = orgUnitRepository.findById(employee.getOrgUnitId()).orElse(null);
        EmployeeModel employeeModel = EmployeeModel.map(employee);
        employeeModel.setOrgUnitText(orgUnit.getName());
        return  employeeModel;
    }
}
