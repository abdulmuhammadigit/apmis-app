package com.clean.application.necessitychart.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteNecessityChartDetailCommandHandler implements IRequestHandler<DeleteNecessityChartDetailCommand,Boolean> {
    NecessityChartDetailRepository necessityChartDetailRepository;
    StageRepository stageRepository;
    NecessityChartRepository necessityChartRepository;
    @Autowired
    public DeleteNecessityChartDetailCommandHandler(NecessityChartDetailRepository necessityChartDetailRepository , StageRepository stageRepository,NecessityChartRepository necessityChartRepository) {
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.stageRepository = stageRepository;
        this.necessityChartRepository = necessityChartRepository;
    }
    @Override
    public Boolean handle(DeleteNecessityChartDetailCommand request) {
        //BRC:check it editable
        
        if(!stageRepository.findById(necessityChartRepository.findById(necessityChartDetailRepository.findById(request.getId()).get().getNeccessityChartId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل حذف نمیباشد!");
        }
        necessityChartDetailRepository.deleteById(request.getId());;

        return true;
    }
    
}
