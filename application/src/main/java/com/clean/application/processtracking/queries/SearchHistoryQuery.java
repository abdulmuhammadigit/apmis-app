package com.clean.application.processtracking.queries;

import java.util.List;

import com.clean.application.processtracking.models.HistoryModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryQuery implements IRequest<List<HistoryModel>> {
    private String entity;
    private int classificationId;
    private long recordId;
}
