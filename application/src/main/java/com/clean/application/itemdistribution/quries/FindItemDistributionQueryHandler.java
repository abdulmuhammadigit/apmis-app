package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemDistributionDetailModel;
import com.clean.application.itemdistribution.models.ItemDistributionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Unit;
import com.clean.domain.entity.stc.*;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.look.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindItemDistributionQueryHandler implements IRequestHandler<FindItemDistributionQuery, ItemDistributionModel> {

    private ItemDistributionRepository itemDistributionRepository;
    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private ItemRequestRepository itemRequestRepository;
    private ItemDetailRepository itemDetailRepository;
    private UnitRepository UnitRepository;
    @Autowired
    FindItemDistributionQueryHandler(
            ItemDistributionRepository itemDistributionRepository,
            ItemDistributionDetailRepository itemDistributionDetailRepository,
            ItemRequestRepository itemRequestRepository,
            ItemDetailRepository itemDetailRepository,
            UnitRepository unitRepository
    ){
        this.itemDistributionRepository = itemDistributionRepository;
        this.itemDistributionDetailRepository = itemDistributionDetailRepository;
        this.itemRequestRepository = itemRequestRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.UnitRepository = unitRepository;
    }
    @Override
    public ItemDistributionModel handle(FindItemDistributionQuery request) {

        ItemDistribution itemDistribution = itemDistributionRepository.findByIdDetail(request.getId());

        if(itemDistribution == null){
            throw new RuntimeException("فورم توزیع دریافت نگردید!");
        }

        List<ItemDistributionDetail> itemDistributionDetailList = itemDistributionDetailRepository.findAllByItemDistributionId(itemDistribution.getId());
        List<ItemDistributionDetailModel> itemDistributionDetailModelList = new ArrayList<>();

        itemDistributionDetailList.forEach(detail->{
            ItemDistributionDetailModel itemDistributionDetailModel = ItemDistributionDetailModel.map(detail);
            itemDistributionDetailModel.setItemDetailText(detail.getItemDetail().getDetail());
            Unit unit =UnitRepository.findByItemId(detail.getItemDetail().getItemId());
            itemDistributionDetailModel.setUnitText(unit.getName());
            itemDistributionDetailModel.setRemain(detail.getItemRequestDetail().getRemain());
            itemDistributionDetailModel.setRequestedQuantity(detail.getItemRequestDetail().getQuantity());
            itemDistributionDetailModelList.add(itemDistributionDetailModel);
        });
        ItemDistributionModel itemDistributionModel = ItemDistributionModel.map(itemDistribution);
        itemDistributionModel.setItemDistributionDetailModels(itemDistributionDetailModelList);
        itemDistributionModel.setItemRequestCode(itemDistribution.getItemRequest().getCode());

        return itemDistributionModel;
    }
}
