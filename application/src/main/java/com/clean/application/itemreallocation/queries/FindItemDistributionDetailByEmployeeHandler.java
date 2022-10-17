package com.clean.application.itemreallocation.queries;

import com.clean.application.itemdistribution.models.ItemDistributionDetailModel;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.look.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FindItemDistributionDetailByEmployeeHandler implements IRequestHandler<FindItemDistributionDetailByEmployee, List<ItemDistributionDetailModel>> {
    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private UnitRepository unitRepository;

    @Autowired
    FindItemDistributionDetailByEmployeeHandler(
            ItemDistributionDetailRepository itemDistributionDetailRepository,
            ItemDetailRepository itemDetailRepository,
            UnitRepository unitRepository
    ) {
        this.itemDistributionDetailRepository = itemDistributionDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    public List<ItemDistributionDetailModel> handle(FindItemDistributionDetailByEmployee request) {
        List<ItemDistributionDetail> itemDistributionDetail = itemDistributionDetailRepository.findAllByItemDistributionByEmployee(request.getEmployeeId(),StatusMapped.DISTRIBUTION_DETAIL_DISTRIBUTED);
        List<ItemDistributionDetailModel> itemDistributionDetailModelList = new ArrayList<>();
        itemDistributionDetail.forEach(detail -> {
            ItemDistributionDetailModel model = new ItemDistributionDetailModel();
            String itemDetail = itemDetailRepository.findById(detail.getItemDetailId()).get().getDetail();
            model.setId(detail.getId());
            model.setItemDistributionId(detail.getItemDistributionId());
            model.setItemRequestDetailId(detail.getItemRequestDetailId());
            model.setItemDetailId(detail.getItemDetailId());
            model.setQuantity(detail.getQuantity());
            model.setRemain((short)(detail.getQuantity() - detail.getReallocatedQuantity()));
            model.setRequestedQuantity(detail.getItemRequestDetail().getBaseQuantity());
            model.setItemDetailText(itemDetail);
            itemDistributionDetailModelList.add(model);
        });

       return itemDistributionDetailModelList;
}
}
