package com.clean.application.item.models;

import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.itm.ItemDetail;
import lombok.AllArgsConstructor;
import lombok.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailModel {
    private int id;
    private String nameText;
    private String detail;
    private String code;
    private String categoryText;
    private boolean consumable;
    private int itemId;
    private String unitText;


    public static ItemDetailModel map(ItemDetail item){
        return ItemDetailModel.builder()
                .id(item.getId())
                .nameText(item.getDetail())
                .detail(item.getCode())
                .build();
    }
}
