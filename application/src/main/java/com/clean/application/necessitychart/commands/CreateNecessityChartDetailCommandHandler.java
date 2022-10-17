package com.clean.application.necessitychart.commands;

import com.clean.application.configuration.queries.GetUnitExchangeValueQuery;
import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.necessitychart.NecessityChartDetailRepository;
import com.clean.persistence.necessitychart.NecessityChartRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNecessityChartDetailCommandHandler
        implements IRequestHandler<CreateNecessityChartDetailCommand, NecessityChartDetailModel> {
    private NecessityChartDetailRepository necessityChartDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private ItemRepository itemRepository;
    private IMediator mediator;
    private StageRepository stageRepository;
    private NecessityChartRepository necessityChartRepository;
    @Autowired
    public CreateNecessityChartDetailCommandHandler(NecessityChartDetailRepository necessityChartDetailRepository,
            ItemDetailRepository itemDetailRepository, IMediator mediator,ItemRepository itemRepository
            ,StageRepository stageRepository,NecessityChartRepository necessityChartRepository) {
        this.necessityChartDetailRepository = necessityChartDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.mediator = mediator;
        this.itemRepository = itemRepository;
        this.stageRepository = stageRepository;
        this.necessityChartRepository = necessityChartRepository;
    }

    @Override
    public NecessityChartDetailModel handle(CreateNecessityChartDetailCommand request) {
        //BRC:check for item duplication
        if(necessityChartDetailRepository.existsByItemDetailIdAndFiscalYearQuarterIdAndIdIsNot(request.getItemDetailId(),request.getFiscalYearQuarterId(),request.getId())){
            throw new RuntimeException("جنس انتخاب شده برای ربع انتخاب شده قبلاً درج گردیده است!");
        }
        //BRC:check for unit of none consumable item. should be same
        Item item = itemRepository.findItemByItemDetial(request.getItemDetailId());
        if(!(item.getConsumable()) && item.getUnitId() != request.getUnitId()){
            throw new RuntimeException("واحد جنس با واحد درخواست شده یکسان نمیباشد!");
        } 
        //BRC:check it editable
        
        if(!stageRepository.findById(necessityChartRepository.findById(request.getNecessityChartId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }

        NecessityChartDetail detail = necessityChartDetailRepository.findById(request.getId()).orElse(null);
        if (detail == null) {
            detail = new NecessityChartDetail();
        }

        // exchange or convert units
        int quantity = mediator.send(new GetUnitExchangeValueQuery(request.getItemDetailId(),
                request.getUnitId(), request.getRequestedQuantity()));
        if (quantity == 0) {
            quantity = request.getRequestedQuantity();
        }
        detail.setBaseQuantity((short) quantity);
        detail.setNeccessityChartId(request.getNecessityChartId());
        detail.setItemDetailId(request.getItemDetailId());
        detail.setRequestedQuantity(request.getRequestedQuantity());
        detail.setUnitId(request.getUnitId());
        detail.setDescription(request.getDescription());
        detail.setFiscalYearQuarterId(request.getFiscalYearQuarterId());
        detail = necessityChartDetailRepository.save(detail);
        NecessityChartDetailModel model = NecessityChartDetailModel.map(detail);
        model.setItemDetailText(itemDetailRepository.findById(detail.getItemDetailId()).get().getDetail());
        return model;
    }

}
