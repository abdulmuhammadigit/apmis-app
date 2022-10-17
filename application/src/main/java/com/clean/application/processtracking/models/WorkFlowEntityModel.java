package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.WorkFlowEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlowEntityModel {
    private int id;
    private String code;
    private String description;

    public static WorkFlowEntityModel map(WorkFlowEntity entity){
        WorkFlowEntityModel model = new WorkFlowEntityModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        return model;
    }
}
