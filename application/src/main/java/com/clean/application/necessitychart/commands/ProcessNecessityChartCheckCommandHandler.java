package com.clean.application.necessitychart.commands;

import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChart;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessNecessityChartCheckCommandHandler implements IRequestHandler<ProcessNecessityChartCheckCommand , Boolean> {
    private NecessityChartRepository necessityChartRepository;
    private NecessityChartDetailRepository necessityChartDetailRepository;
    @Autowired
    public ProcessNecessityChartCheckCommandHandler(NecessityChartRepository necessityChartRepository , NecessityChartDetailRepository necessityChartDetailRepository) {
        this.necessityChartRepository = necessityChartRepository;
        this.necessityChartDetailRepository = necessityChartDetailRepository;
    }
    @Override
    public Boolean handle(ProcessNecessityChartCheckCommand request) {
        if(request.getBusinessRule().equals(BusinessRuleMapped.NecessityChartDetailCheck)){
            if(!necessityChartDetailRepository.existsByNecessityChartId(request.getRecordId())){
                throw new RuntimeException("اجناس برای درخواست وجود ندارد!");
            }
        }
        else if(request.getBusinessRule().equals(BusinessRuleMapped.NecessityChartCommissionDecision)){
           // List<NecessityChartDetail> list= necessityChartDetailRepository.findByNecessityChartIdAndCommissionDecisionIsNull(request.getRecordId());
            if(necessityChartDetailRepository.existsByNecessityChartIdAndCommissionDecisionIsNull(request.getRecordId()) ){
                throw new RuntimeException("لطف نموده برای تمام اجناس نظریه های کمیسیون را درج نمائید!");
            }
        }
        NecessityChart chart= necessityChartRepository.findById(request.getRecordId()).get();
                chart.setStageId(request.getStageId());
                necessityChartRepository.save(chart);
        return true;
    }
    
}
