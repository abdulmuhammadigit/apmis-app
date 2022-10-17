package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.Connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionModel {
    private int id;
    private int stageId;
    private int toStageId;
    private int businessRuleId;
    private String stageText;
    private String toStageText;
    private String businessRuleText;
    public static ConnectionModel map(Connection connection){
        ConnectionModel model = new ConnectionModel();
        model.setId(connection.getId());
        model.setStageId(connection.getStageId());
        model.setToStageId(connection.getToStageId());
        model.setBusinessRuleId(connection.getBusinessRuleId());
        return model;
    }
}
