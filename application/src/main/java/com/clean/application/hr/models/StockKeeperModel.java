package com.clean.application.hr.models;

import com.clean.domain.entity.hr.StockKeeper;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockKeeperModel {
    private short id;
    private int employeeId;
    private short stockKeeperTypeId;
    private short itemCategoryId;
    private Integer locationId;
    private Long userId;
    private String name;
    private String lastName;
    private String fatherName;
    private String itemCategoryText;
    private String stockKeeperTypeText;
    private String locationText;

    public static StockKeeperModel map(StockKeeper stockKeeper){
        StockKeeperModel model = new StockKeeperModel();
        model.setId(stockKeeper.getId());
        model.setEmployeeId(stockKeeper.getEmployeeId());
        model.setStockKeeperTypeId(stockKeeper.getStockKeeperTypeId());
        model.setItemCategoryId(stockKeeper.getItemCategoryId());
        model.setLocationId(stockKeeper.getLocationId());
        model.setUserId(stockKeeper.getUserId());
        return model;
    }
}
