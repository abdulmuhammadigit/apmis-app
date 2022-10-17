package com.clean.application.itemdistribution.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReceiptDistributedModel {
    private long id;
    private String code;
    private Date receiveDate;
    private String name;
    private String lastName;
    private String fatherName;
    private String itemReceiptTypeText;
    private String item;
    private String itemDetail;
    private String unitText;
    private double price;
}