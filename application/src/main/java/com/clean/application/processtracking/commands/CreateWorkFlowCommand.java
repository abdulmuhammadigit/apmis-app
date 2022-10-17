package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkFlowCommand implements IRequest<WorkFlowModel>{
    private int id;
    private String name;
    private int entityId;
    private Integer classificationId;
    private Integer parentId;
}
