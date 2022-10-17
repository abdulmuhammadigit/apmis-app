package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateConnectionCommand;
import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Connection;
import com.clean.persistence.processtracking.ConnectionRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateConnectionCommandHandler implements IRequestHandler<CreateConnectionCommand,ConnectionModel> {
    private ConnectionRepository connectionRepository;
    @Autowired
    public CreateConnectionCommandHandler(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }
    @Override
    public ConnectionModel handle(CreateConnectionCommand request) {

        if(request.getStageId() == request.getToStageId()){
            throw new RuntimeException("Same Stage And To Stage Selected!");
        }

        if(connectionRepository.existsByStageIdAndToStageIdAndIdIsNot(request.getStageId(), request.getToStageId(), request.getId())){
            throw new RuntimeException("Connection Exist!");
        }

        Connection connection = connectionRepository.findById(request.getId()).orElse(null);
        if(connection == null){
            connection = new Connection();
        }
        connection.setStageId(request.getStageId());
        connection.setToStageId(request.getToStageId());
        connection.setBusinessRuleId(request.getBusinessRuleId());
        connection = connectionRepository.save(connection);
        return ConnectionModel.map(connection);
    }
    
}
