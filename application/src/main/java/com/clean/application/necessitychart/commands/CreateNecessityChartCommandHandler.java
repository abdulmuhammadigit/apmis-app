package com.clean.application.necessitychart.commands;

import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.utils.EntityIds;
import com.clean.common.constant.StageMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChart;
import com.clean.persistence.necessitychart.NecessityChartRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNecessityChartCommandHandler
        implements IRequestHandler<CreateNecessityChartCommand, NecessityChartModel> {
    private NecessityChartRepository necessityChartRepository;
    private IMediator mediator;
    private StageRepository stageRepository;
    @Autowired
    CreateNecessityChartCommandHandler(NecessityChartRepository necessityChartRepository,IMediator mediator,StageRepository stageRepository){
        this.necessityChartRepository = necessityChartRepository;
        this.mediator = mediator;
        this.stageRepository = stageRepository;
    }

    @Override
    public NecessityChartModel handle(CreateNecessityChartCommand request) {

        //BRC:check for duplication
        if(necessityChartRepository.existsByFiscalYearIdAndOrgUnitIdAndIdIsNot(request.getFiscalYearId(),request.getOrgUnitId(),request.getId())){
            throw new RuntimeException("برای ریاست مذکور در سال مالی انتخاب شده چارت صرفیه درج سیستم گردیده است!");
        }
        // save or update necessity chart entity
        NecessityChart necessityChart = necessityChartRepository.findById(request.getId()).orElse(null);
        if (necessityChart == null) {
            necessityChart = new NecessityChart();
            necessityChart.setCode(request.getCode());
        }else{
            //check for stage is editable
            if(!stageRepository.findById(necessityChart.getStageId()).get().isEditable()){
                throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
            }
        }
        
        

        necessityChart.setFiscalYearId(request.getFiscalYearId());
        necessityChart.setOrgUnitId(request.getOrgUnitId());
        necessityChart = necessityChartRepository.save(necessityChart);

        
        // initial process tracking
        if(request.getId() == 0){
            InitialHistoryCommandResult result = mediator.send(
                    CreateInitialHistoryCommand.builder()
                    .recordId(necessityChart.getId())
                    .entityId(EntityIds.NecessityChart)
                    .build()
            );

            necessityChart.setStageId(result.getToStage().getId());
            necessityChartRepository.save(necessityChart);
        }
        
        return NecessityChartModel.map(necessityChart);
    }
}
