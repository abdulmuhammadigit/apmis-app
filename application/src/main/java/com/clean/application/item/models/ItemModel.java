package com.clean.application.item.models;

import com.clean.domain.entity.itm.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ItemModel {
    private int id;
    private String name;
    private boolean consumable;
    private short categoryId;
    private short unitId;
    private String categoryText;
    private String unitText;

    public static ItemModel map(Item item){
        return ItemModel.builder()
        .id(item.getId())
        .name(item.getName())
        .consumable(item.getConsumable())
        .categoryId(item.getCategoryId())
        .unitId(item.getUnitId())
        .build();
    }
}
