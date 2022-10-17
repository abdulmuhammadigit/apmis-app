package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.WorkFlow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlowModel {
    private int id;
    private String name;
    private int entityId;
    private Integer classificationId;
    private Integer parentId;
    private String entityText;
    private String classificationText;
    private String ParentText;
    public static WorkFlowModel map(WorkFlow workFlow){
        WorkFlowModel model = new WorkFlowModel();
        model.setId(workFlow.getId());
        model.setName(workFlow.getName());
        model.setEntityId(workFlow.getEntityId());
        model.setClassificationId(workFlow.getClassificationId());
        model.setParentId(workFlow.getParentId());
        return model;
    }
}
