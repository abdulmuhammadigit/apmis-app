package com.clean.application.report.models;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.domain.entity.itm.ItemDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemQuantityModel {
    private int id;
    private String text;
    private Integer count;

    public static ItemDetailModel map(ItemDetail item){
        return ItemDetailModel.builder()
                .id(item.getId())
                .itemId(item.getItemId())
                .build();
    }
}
