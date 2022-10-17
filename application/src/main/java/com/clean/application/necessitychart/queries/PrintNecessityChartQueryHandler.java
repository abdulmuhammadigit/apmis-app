package com.clean.application.necessitychart.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.necessitychart.models.NecessityChartDetailPrintModel;
import com.clean.application.necessitychart.models.NecessityChartPrintModel;
import com.clean.common.constant.FiscalYearQuarterMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.NecessityChart;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PrintNecessityChartQueryHandler implements IRequestHandler<PrintNecessityChartQuery,NecessityChartPrintModel> {
    private NecessityChartRepository necessityChartRepository;
    private NecessityChartDetailRepository necessityChartDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemRepository itemRepository;
    @Autowired
    public PrintNecessityChartQueryHandler(
        NecessityChartRepository necessityChartRepository,
        NecessityChartDetailRepository necessityChartDetailRepository,
        ItemDetailRepository itemDetailRepository,
        ItemRepository itemRepository) {
        this.necessityChartRepository = necessityChartRepository;
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.itemRepository = itemRepository;
    }
    @Override
    public NecessityChartPrintModel handle(PrintNecessityChartQuery request) {
        NecessityChart necessityChart=necessityChartRepository.findNecessityChartDetial(request.getId());
        
        List<NecessityChartDetail> necessityChartDetails = necessityChartDetailRepository.findByNecessityChartId(necessityChart.getId());
        
        List<Integer> itemDetailIdList = necessityChartDetails.stream().map(e->e.getItemDetailId()).distinct().collect(Collectors.toList());
        
        List<NecessityChartDetailPrintModel> detailPrintModelList = new ArrayList<>();

        itemDetailIdList.forEach(e->{
            NecessityChartDetailPrintModel detailPrint = new NecessityChartDetailPrintModel();
            detailPrint.setItemDetailId(e);
            detailPrint.setItemDetailText(itemDetailRepository.findById(e).get().getDetail());
            detailPrint.setUnitText(itemRepository.findItemByItemDetial(e).getUnit().getName());
            necessityChartDetails.stream().filter(f->f.getItemDetailId() == e).sorted((a,b)->Integer.compare(a.getFiscalYearQuarterId(), b.getFiscalYearQuarterId())).forEach(map->{
                
                if(map.getFiscalYearQuarterId() == FiscalYearQuarterMapped.firstQuarter){
                    detailPrint.setFirstQuarter(map.getBaseQuantity());
                }
                else if(map.getFiscalYearQuarterId() == FiscalYearQuarterMapped.secondQuarter){
                    detailPrint.setSecondQuarter(map.getBaseQuantity());
                }
                else if(map.getFiscalYearQuarterId() == FiscalYearQuarterMapped.thirdQuarter){
                    detailPrint.setThirdQuarter(map.getBaseQuantity());
                }
                else if(map.getFiscalYearQuarterId() == FiscalYearQuarterMapped.fourthQuarter){
                    detailPrint.setFourthQuarter(map.getBaseQuantity());
                }
            });
            detailPrintModelList.add(detailPrint);
        });
        
        NecessityChartPrintModel model= new NecessityChartPrintModel();
        model.setCode(necessityChart.getCode());
        model.setFiscalYear(necessityChart.getFiscalYear().getShamsiYear());
        model.setOrgUnitText(necessityChart.getOrgUnit().getName());
        model.setDetailPrintModelList(detailPrintModelList);
        return model;
    }
    
}
