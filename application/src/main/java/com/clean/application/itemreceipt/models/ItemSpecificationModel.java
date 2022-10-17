package com.clean.application.itemreceipt.models;

import com.clean.domain.entity.stc.ItemSpecification;
import lombok.Data;

import java.sql.Date;

@Data
public class ItemSpecificationModel {
    private long id;
    private String serialNumber;
    private String tagNumber;
    private Date expirationDate;
    private String location;

    public static ItemSpecificationModel map(ItemSpecification entity){
        ItemSpecificationModel model = new ItemSpecificationModel();
        model.setId(entity.getId());
        model.setSerialNumber(entity.getSerialNumber());
        model.setTagNumber(entity.getTagNumber());
        model.setExpirationDate(entity.getExpirationDate());
        model.setLocation(entity.getLocation());
        return model;
    }
}
