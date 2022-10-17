package com.clean.application.itemrequest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemRequesSearchModel {
    private long id;
    private String code;
    private boolean completed;
    private Date date;
    private String description;
    private String documentNumber;
    private int employeeId;
    private String employeeName;
    private String employeeLastName;
    private String employeeFatherName;
}
