package com.clean.application.item.commands;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.itm.Attribute;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
public class CreateAttributeCommand implements IRequest<Boolean> {
    private int id;
    private short categoryId;
    private String name;
    private short dataTypeId;
}
