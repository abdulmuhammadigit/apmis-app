package com.clean.application.itemreceipt.models;

import com.clean.domain.entity.stc.ItemReceiptDetail;
import lombok.*;
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReceiptDetailModel {
    private long id;
    private long itemReceiptId;
    private int itemDetailId;
    private short unitId;
    private short quantity;
    private short baseQuantity;
    private double price;
    private String description;
    private String itemDetailText;
    private boolean consumable;
    private boolean localProduction;
    private String unitText;
    private String baseUnitText;
    public static ItemReceiptDetailModel map(ItemReceiptDetail itemReceiptM7Detail)
    {
        ItemReceiptDetailModel model = new ItemReceiptDetailModel();
        model.setId(itemReceiptM7Detail.getId());
        model.setItemReceiptId(itemReceiptM7Detail.getItemReceiptId());
        model.setItemDetailId(itemReceiptM7Detail.getItemDetailId());
        model.setUnitId(itemReceiptM7Detail.getUnitId());
        model.setQuantity(itemReceiptM7Detail.getQuantity());
        model.setBaseQuantity(itemReceiptM7Detail.getBaseQuantity());
        model.setPrice(itemReceiptM7Detail.getPrice());
        model.setDescription(itemReceiptM7Detail.getDescription());
        model.setLocalProduction(itemReceiptM7Detail.isLocalProduct());
        return model;
    }
}
