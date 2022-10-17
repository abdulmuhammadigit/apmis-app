package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.StageModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageCommand implements IRequest<StageModel> {
    private Integer id;
    private String name;
    private int stageTypeId;
    private int workFlowId;
    private Boolean isActive;
    private Boolean isEditable;
}
