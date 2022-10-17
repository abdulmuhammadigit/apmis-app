package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.EntityClassification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityClassificationModel {
    private int id;
    private String name;
    private int entityId;
    private String entityText;
    public static EntityClassificationModel map(EntityClassification classification){
        EntityClassificationModel model = new EntityClassificationModel();
        model.setId(classification.getId());
        model.setEntityId(classification.getEntityId());
        model.setName(classification.getName());
        return model;
    }
}
