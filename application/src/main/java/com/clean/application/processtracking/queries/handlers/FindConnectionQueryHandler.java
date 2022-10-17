package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Connection;
import com.clean.persistence.processtracking.ConnectionRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindConnectionQueryHandler implements IRequestHandler<FindConnectionQuery,ConnectionModel> {
    private ConnectionRepository connectionRepository;
    @Autowired
    public FindConnectionQueryHandler(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }
    @Override
    public ConnectionModel handle(FindConnectionQuery request) {
        Connection connection = connectionRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Not Found!"));
        return ConnectionModel.map(connection);
    }
    
}
