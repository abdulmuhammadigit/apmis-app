package com.clean.application.processtracking.commands;

import java.util.List;

import com.clean.application.processtracking.models.HistoryModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHistoryCommand implements IRequest<List<HistoryModel>> {
    private int currentStageId;
    private int toStageId;
    private long recordId;
    private int classificationId;
    private String entity;
    private String description;
}
