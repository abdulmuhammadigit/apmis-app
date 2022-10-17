package com.clean.application.configuration.models;

import com.clean.domain.entity.conf.UnitExchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitExchangeModel {
    private int id;
    private int unitId;
    private short quantity;
    private int toUnitId;
    private Integer itemId;
    private String unitText;
    private String toUnitText;
    private String itemText;

    public static UnitExchangeModel map(UnitExchange entity){
        UnitExchangeModel model = new UnitExchangeModel();
        model.setId(entity.getId());
        model.setUnitId(entity.getUnitId());
        model.setQuantity(entity.getQuantity());
        model.setToUnitId(entity.getToUnitId());
        model.setItemId(entity.getItemId());
        return model;
    }
}
