package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.Stage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageModel {
    private int id;
    private String name;
    private int stageTypeId;
    private int workFlowId;
    private Boolean isActive;
    private Boolean isEditable;
    private String stageTypeText;
    private String workFlowText;

    public static StageModel map(Stage stage){
        StageModel model = new StageModel();
        model.setId(stage.getId());
        model.setName(stage.getName());
        model.setStageTypeId(stage.getStageTypeId());
        model.setWorkFlowId(stage.getWorkFlowId());
        model.setIsActive(stage.isActive());
        model.setIsEditable(stage.isEditable());
        return model;
    }
}
