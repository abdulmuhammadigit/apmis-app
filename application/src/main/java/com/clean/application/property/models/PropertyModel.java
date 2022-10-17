package com.clean.application.property.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyModel {
    private String employeeCode;
    private String employee;
    private String detail;
    private String itemCode;
    private String itemRecieptCode;
    private Date distributionDate;
    private double itemPrice;
    private String tagNumber;
    private Date expirationDate;
    private String serialNumber;
}
