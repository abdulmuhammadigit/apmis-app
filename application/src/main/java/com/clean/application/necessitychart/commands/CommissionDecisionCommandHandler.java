package com.clean.application.necessitychart.commands;

import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionDecisionCommandHandler implements IRequestHandler<CommissionDecisionCommand, NecessityChartDetailModel> {
    private NecessityChartDetailRepository necessityChartDetailRepository;
    private NecessityChartRepository necessityChartRepository;
    private StageRepository stageRepository;

    @Autowired
    CommissionDecisionCommandHandler(NecessityChartDetailRepository necessityChartDetailRepository,StageRepository stageRepository,NecessityChartRepository necessityChartRepository){
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.stageRepository = stageRepository;
        this.necessityChartRepository = necessityChartRepository;
    }

    @Override
    public NecessityChartDetailModel handle(CommissionDecisionCommand request) {

        

        NecessityChartDetail necessityChartDetail = necessityChartDetailRepository.findById(request.getNecessityChartDetailId()).orElseThrow(()->new RuntimeException("چارت صرفیه دریافت نگردید!"));
        
        
        
        if(request.isSubmited()){
            if(request.getCommissionDecision() > necessityChartDetail.getCommissionDecision()){
                throw new RuntimeException("مقدار توزیع شده نمیتواند بزرگتر از نظر کمیسیون باشد!");
            }
            necessityChartDetail.setSubmitedQuantity(request.getCommissionDecision());

        }else{
            //BRC:check it editable
        if(!stageRepository.findById(necessityChartRepository.findById(necessityChartDetail.getNeccessityChartId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }
            if(request.getCommissionDecision() > necessityChartDetail.getBaseQuantity()){
                throw new RuntimeException("مقدار نظر کمیسیون نمیتواند بزرگتر از نظر کمیسیون باشد!");
            }
            necessityChartDetail.setCommissionDecision(request.getCommissionDecision());
        }
        necessityChartDetail= necessityChartDetailRepository.save(necessityChartDetail);
        return NecessityChartDetailModel.map(necessityChartDetail);
    }
}
