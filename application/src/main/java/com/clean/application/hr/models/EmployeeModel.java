package com.clean.application.hr.models;

import com.clean.domain.entity.hr.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmployeeModel {
    private int id;
    private String code;
    private String name;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private String position;
    private int orgUnitId;
    private String orgUnitText;
    private String cardId;

    public  static EmployeeModel map(Employee employee){
        EmployeeModel model = new EmployeeModel();
        model.setId(employee.getId());
        model.setCode(employee.getCode());
        model.setName(employee.getName());
        model.setLastName(employee.getLastName());
        model.setFatherName(employee.getFatherName());
        model.setGrandFatherName(employee.getGrandFatherName());
        model.setPosition(employee.getPosition());
        model.setOrgUnitId(employee.getOrgUnitId());
        model.setCardId(employee.getCardId());
        return  model;
    }
}
