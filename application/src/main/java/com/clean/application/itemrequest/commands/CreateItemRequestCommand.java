package com.clean.application.itemrequest.commands;

import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.application.itemrequest.models.ItemRequestModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
public class CreateItemRequestCommand implements IRequest<ItemRequestModel> {

    private long id;
    private Date date;
    private String description;
    private String documentNumber;
    private int employeeId;
    private String department;
    private String code;
    List<ItemRequestDetailModel> detailsModel;
}
