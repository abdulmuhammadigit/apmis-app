package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.WorkFlowEntityModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkFlowEntityCommand implements IRequest<WorkFlowEntityModel> {
    private int id;
    private String code;
    private String description;
}
