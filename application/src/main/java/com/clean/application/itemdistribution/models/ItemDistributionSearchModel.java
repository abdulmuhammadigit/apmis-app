package com.clean.application.itemdistribution.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ItemDistributionSearchModel {
    private long id;
    private String code;
    private String documentNumber;
    private Date date;
    private long itemRequestId;
    private String description;
    private String itemRequestCode;
}
