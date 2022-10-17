package com.clean.application.report.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesAllocatedItemsModel {
    private long id;
    private String item;
    private String itemDetail;
    private String serialNumber;
    private String tagNumber;
    private int employeeId;
    private long itemDistributedSpecificationId;
}
