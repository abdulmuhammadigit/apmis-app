package com.clean.application.itemdistribution.models;

import com.clean.domain.entity.stc.ItemDistribution;
import lombok.*;


import java.sql.Date;
import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDistributionModel {
    private long id;
    private String code;
    private String documentNumber;
    private Date date;
    private long itemRequestId;
    private String description;
    private int stageId;
    private String itemRequestCode;
    private String itemRequestShamsiDate;
    private List<ItemDistributionDetailModel> itemDistributionDetailModels;

    public static ItemDistributionModel map(ItemDistribution itemDistribution)
    {
        ItemDistributionModel model = new ItemDistributionModel();
        model.setId(itemDistribution.getId());
        model.setCode(itemDistribution.getCode());
        model.setDocumentNumber(itemDistribution.getDocumentNumber());
        model.setDate(itemDistribution.getDate());
        model.setItemRequestId(itemDistribution.getItemRequestId());
        model.setDescription(itemDistribution.getDescription());
        model.setStageId(itemDistribution.getStageId());
        return model;
    }
}
