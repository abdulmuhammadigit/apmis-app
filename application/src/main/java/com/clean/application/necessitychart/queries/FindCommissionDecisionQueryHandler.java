package com.clean.application.necessitychart.queries;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChart;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class FindCommissionDecisionQueryHandler implements IRequestHandler<FindCommissionDecisionQuery , NecessityChartModel> {
    private NecessityChartRepository necessityChartRepository;
    private NecessityChartDetailRepository necessityChartDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemRepository itemRepository;
    @Autowired
    FindCommissionDecisionQueryHandler(NecessityChartRepository necessityChartRepository, NecessityChartDetailRepository necessityChartDetailRepository, 
    ItemDetailRepository itemDetailRepository,
    ItemRepository itemRepository){
        this.necessityChartRepository = necessityChartRepository;
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.itemRepository = itemRepository;
    }
    @Override
    public NecessityChartModel handle(FindCommissionDecisionQuery request) {
        NecessityChart necessityChart=necessityChartRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("چارت صرفیه دریافت نگردید !"));
        List<NecessityChartDetail> necessityChartDetails = necessityChartDetailRepository.findByNecessityChartId(necessityChart.getId());
        List<NecessityChartDetailModel> necessityChartDetailModels =necessityChartDetails.stream().
        map(map->{
            NecessityChartDetailModel model = new NecessityChartDetailModel();
            model.setId(map.getId());
            model.setBaseQuantity(map.getBaseQuantity());
            model.setBaseUnitText(itemRepository.findItemByItemDetial(map.getItemDetailId()).getUnit().getName());
            model.setCommissionDecision(map.getCommissionDecision());
            model.setDescription(map.getDescription());
            model.setFiscalYearQuarterId(map.getFiscalYearQuarterId());
            model.setFiscalYearQuarterText(map.getFiscalYearQuarter().getName());
            model.setItemDetailId(map.getItemDetailId());
            model.setItemDetailText(map.getItemDetail().getDetail());
            model.setNeccessityChartId(map.getNeccessityChartId());
            model.setRequestedQuantity(map.getRequestedQuantity());
            model.setUnitId(map.getUnitId());
            model.setUnitText(map.getUnit().getName());
            model.setSubmitedQuantity(map.getSubmitedQuantity());
            return model;
        })
        .collect(Collectors.toList());
        NecessityChartModel model= NecessityChartModel.map(necessityChart);
        model.setOrgUnitText(necessityChart.getOrgUnit().getName());
        model.setFiscalYearText(necessityChart.getFiscalYear().getShamsiYear());
        model.setDetailModelList(necessityChartDetailModels);
        return model;
    }
    
}
