package com.clean.application.itemreallocation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReallocationSpecificationModel {
    private long id;
    private String item;
    private String itemDetail;
    private long itemSpecificationId;
    private String serialNumber;
    private String tagNumber;
    private String location;
    private Date expirationDate;
    private long itemReceiptDetailId;
    private int boardStatusId;
    private float price;
    private double mainPrice;
    private String boardStatusText;

    private long itemReallocationId;
    private int stageId;

}
