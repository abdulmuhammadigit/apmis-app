package com.clean.application.itemreceipt.commands;

import java.sql.Date;

import com.clean.application.itemreceipt.models.ItemSpecificationModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreateItemSpecificationCommand implements IRequest<ItemSpecificationModel> {
    
    private long id;
    private long itemReceiptDetailId;
    private String serialNumber;
    private String tagNumber;
    private Date expirationDate;
    private String location;
}

