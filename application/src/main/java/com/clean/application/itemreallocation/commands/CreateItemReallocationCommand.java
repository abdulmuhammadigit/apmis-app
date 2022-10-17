package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationDetailModel;
import com.clean.application.itemreallocation.models.ItemReallocationModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemReallocationCommand implements IRequest<ItemReallocationModel> {
    private long id;
    private String code;
    private String documentNumber;
    private Date date;
    private String description;
    private int employeeId;
}
