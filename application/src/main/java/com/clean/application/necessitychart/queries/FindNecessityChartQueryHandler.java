package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChart;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class FindNecessityChartQueryHandler implements IRequestHandler<FindNecessityChartQuery, NecessityChartModel> {

    private NecessityChartRepository necessityChartRepository;
    private NecessityChartDetailRepository necessityChartDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    FindNecessityChartQueryHandler(NecessityChartRepository necessityChartRepository, NecessityChartDetailRepository necessityChartDetailRepository, ItemDetailRepository itemDetailRepository){
        this.necessityChartRepository = necessityChartRepository;
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
    }

    @Override
    public NecessityChartModel handle(FindNecessityChartQuery request) {
        NecessityChart necessityChart=necessityChartRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("چارت صرفیه دریافت نگردید !"));
        List<NecessityChartDetail> necessityChartDetails = necessityChartDetailRepository.findByNecessityChartId(necessityChart.getId());
        List<NecessityChartDetailModel> necessityChartDetailModels =necessityChartDetails.stream().map(NecessityChartDetailModel::map).collect(Collectors.toList());
        necessityChartDetailModels.stream().forEach(e -> {
            e.setItemDetailText(itemDetailRepository.findById(e.getItemDetailId()).get().getDetail());
        });
        NecessityChartModel model= NecessityChartModel.map(necessityChart);
        model.setDetailModelList(necessityChartDetailModels);
        return model;

    }
}
