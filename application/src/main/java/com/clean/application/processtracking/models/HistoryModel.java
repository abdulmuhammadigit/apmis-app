package com.clean.application.processtracking.models;

import java.sql.Timestamp;
import java.util.Date;

import com.clean.domain.entity.prc.History;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryModel {
    private long id;
    private int entityId;
    private long recordId;
    private int workFlowId;
    private Date date;
    private String description;
    private int toStageId;
    private int fromStageId;
    //private int userId;
    //private int toUserId;
    private String stageText;
    private String toStageText;
    private String entityText;
    private String workFlowText;
    //private String userText;
    //private String toUserText;

    public static HistoryModel map(History history){
        HistoryModel model = new HistoryModel();
        model.setId(history.getId());
        model.setEntityId(history.getEntityId());
        model.setRecordId(history.getRecordId());
        model.setWorkFlowId(history.getWorkFlowId());  
        model.setDate(history.getDate());
        model.setDescription(history.getDescription());
        model.setToStageId(history.getToStageId());
        model.setFromStageId(history.getFromStageId());
        //model.setUserId(history.getUserId());
        //model.setToUserId(history.getToUserId());
        return model;
    }
}
