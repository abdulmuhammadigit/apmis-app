package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateConnectionCommand implements IRequest<ConnectionModel>{
    private int id;
    private int stageId;
    private int toStageId;
    private int businessRuleId;
}
