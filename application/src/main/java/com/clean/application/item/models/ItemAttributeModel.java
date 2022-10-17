package com.clean.application.item.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@AllArgsConstructor
public class ItemAttributeModel {
    private int id;
    private int attributeId;
    private int itemId;
    private String attributeText;
    private String itemText;
}
