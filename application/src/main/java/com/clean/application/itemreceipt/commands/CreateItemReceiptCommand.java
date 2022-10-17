package com.clean.application.itemreceipt.commands;

import com.clean.application.itemreceipt.models.ItemReceiptModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemReceiptCommand implements IRequest<ItemReceiptModel> {

    private long id;
    //private String documentNumber;
    //private Date documentDate;
    private short itemReceiptTypeId;
    private Date receiveDate;
    private String billNumber;
    private short stockKeeperId;
    private String description;
    private String code;
    private int donorId;
    private String m16Number;
    private String m3Number;
}
