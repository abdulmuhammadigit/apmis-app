package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.HistoryModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.History;
import com.clean.persistence.processtracking.HistoryRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindHistoryQueryHandler implements IRequestHandler<FindHistoryQuery,HistoryModel> {
    private HistoryRepository historyRepository;
    @Autowired
    public FindHistoryQueryHandler(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    @Override
    public HistoryModel handle(FindHistoryQuery request) {
        History history = historyRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Not Found!"));

        return HistoryModel.map(history);
    }
    
}
