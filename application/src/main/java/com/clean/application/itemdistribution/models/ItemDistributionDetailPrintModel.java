package com.clean.application.itemdistribution.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDistributionDetailPrintModel {
    private short quantity;
    private String ItemReceiptCode;
    private short stockKeeperId;
    private String ItemDetailText;
    private double price;
}
