package com.clean.application.itemreceipt.commands;

import com.clean.application.itemreceipt.models.ItemReceiptDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemReceiptDetailCommand implements IRequest<ItemReceiptDetailModel> {
    private long id;
    private long itemReceiptId;
    private int itemDetailId;
    private short unitId;
    private short quantity;
    private double price;
    private String description;
    private boolean localProduction;
}
