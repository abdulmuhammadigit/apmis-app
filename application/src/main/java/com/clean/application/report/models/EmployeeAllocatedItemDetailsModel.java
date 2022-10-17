package com.clean.application.report.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAllocatedItemDetailsModel {
    private long id;
    private String item;
    private String itemDetail;
    private String RequestCode;
    private Date RequestDate;
    private String DistributionCode;
    private Date DistributionDate;
}
