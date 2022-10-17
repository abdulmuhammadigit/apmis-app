package com.clean.application.itemdistribution.models;

import java.util.Date;
import java.util.List;

import com.clean.domain.entity.stc.ItemDistribution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDistributionPrintModel {
    private String code;
    private String documentNumber;
    private Date date;
    private long itemRequestId;
    private String description;
    private String itemRequestCode;
    private String itemRequestShamsiDate;
    private String itemRequestOrgUnitText;
    private String employeeName;
    private String employeeLastName;
    private String employeeFatherName;
    private String stockKeeperName;
    private String stockKeeperLastName;
    private String stockKeeperFatherName;
    private List<ItemDistributionDetailPrintModel> printModelList;

    public static ItemDistributionPrintModel map(ItemDistribution itemDistribution)
    {
        ItemDistributionPrintModel model = new ItemDistributionPrintModel();
        model.setCode(itemDistribution.getCode());
        model.setDocumentNumber(itemDistribution.getDocumentNumber());
        model.setDate(itemDistribution.getDate());
        model.setItemRequestId(itemDistribution.getItemRequestId());
        model.setDescription(itemDistribution.getDescription());
        return model;
    }
}
