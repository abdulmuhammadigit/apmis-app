package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.ConnectionRepository;
import com.clean.application.processtracking.queries.*;
import com.clean.application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class FindStageConnectionQueryHandler implements IRequestHandler<FindStageConnectionQuery, List<ConnectionModel>> {
    private ConnectionRepository connectionRepository;
    private UserService userSerivce;
    @Autowired
    public FindStageConnectionQueryHandler(ConnectionRepository connectionRepository, UserService userService){
        this.connectionRepository = connectionRepository;
        this.userSerivce = userService;
    }
    @Override
    public List<ConnectionModel> handle(FindStageConnectionQuery request) {

        return connectionRepository.findAllByStageIdAndUserId(request.getStageId(),userSerivce.getUserId()).stream().map(entity->{
            ConnectionModel model= new ConnectionModel();
            model.setId(entity.getId());
            model.setToStageId(entity.getToStageId());
            model.setToStageText(entity.getToStage().getName());
            model.setBusinessRuleText(entity.getBusinessRule().getName());
            return model;
        }).collect(Collectors.toList());
    }
    
}
