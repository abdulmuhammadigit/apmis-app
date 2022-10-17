package com.clean.application.item.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemAttributeValueRequestModel {
    private int id;
    private int attributeId;
    private Short unitId;
    private String value;
}
