package com.clean.application.item.models;

import com.clean.domain.entity.itm.Attribute;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AttributeModel{
    private int id;
    private short categoryId;
    private String name;
    private short dataTypeId;
    private String categoryText;
    private String dataTypeText;

    public static AttributeModel map(Attribute attribute){
        return AttributeModel.builder()
                .id(attribute.getId())
                .categoryId(attribute.getCategoryId())
                .dataTypeId(attribute.getDataTypeId())
                .name(attribute.getName())
                .build();
    }
}
