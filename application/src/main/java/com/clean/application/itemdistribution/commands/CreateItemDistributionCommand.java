package com.clean.application.itemdistribution.commands;

import com.clean.application.itemdistribution.models.ItemDistributionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@AllArgsConstructor
@Data
public class CreateItemDistributionCommand implements IRequest<ItemDistributionModel> {
    private long id;
    private String documentNumber;
    private Date date;
    private long itemRequestId;
    private String code;
    private String description;
}
