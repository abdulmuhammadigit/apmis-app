package com.clean.application.itemreceipt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReceiptSearchModel {
    private long id;
    private String code;
    private String itemReceiptTypeName;
    private short itemReceiptTypeId;
    private Date receiveDate;
    private String billNumber;
    private String stockKeeperName;
    private short stockKeeperId;
    private String description;
    private String stockKeeperLastname;
    private String stockKeeperFatherName;
}
