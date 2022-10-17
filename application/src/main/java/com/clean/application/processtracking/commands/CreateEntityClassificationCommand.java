package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEntityClassificationCommand implements IRequest<EntityClassificationModel>{
    private int id;
    private String name;
    private int entityId;
}
