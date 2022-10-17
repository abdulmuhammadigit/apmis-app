package com.clean.application.report.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReceiptQuantityModel {
    private long id;
    private String name;
    private int itemReceiptCount;
    private int itemCount;
    private double priceTotal;

}
