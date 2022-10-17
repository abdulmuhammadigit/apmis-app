package com.clean.application.item.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAttributeValueModel {
    private int id;
    private int attributeId;
    private int itemDetailId;
    private short unitId;
    private String value;
    private String attributeText;
    private String dataTypeAbbreviation;
    private String dataTypeName;
}
