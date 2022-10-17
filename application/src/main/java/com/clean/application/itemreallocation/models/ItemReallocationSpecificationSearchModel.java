package com.clean.application.itemreallocation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReallocationSpecificationSearchModel {
    private long id;
    private Integer itemDetailId;
    private String itemDetail;
    private long itemSpecificationId;
    private String serialNumber;
    private String tagNumber;
    private String location;
    private Date expirationDate;
    private int depreciationDay;
    private long itemReceiptDetailId;
    private int statusId;
    private float reallocatedPrice;
}
