package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FindItemRequestDetailQueryHandler implements IRequestHandler<FindItemRequestDetailQuery, List<ItemRequestDetailModel>> {
    private ItemRequestDetailRepository itemRequestDetailRepository;

    @Autowired
    FindItemRequestDetailQueryHandler(ItemRequestDetailRepository itemRequestDetailRepository
    ) {
        this.itemRequestDetailRepository = itemRequestDetailRepository;
    }

    @Override
    public List<ItemRequestDetailModel> handle(FindItemRequestDetailQuery request) {
        List<ItemRequestDetail> itemRequestDetails;

        if(request.isNotDistributed()){
            itemRequestDetails = itemRequestDetailRepository.findAllByItemRequestNotDistributed(request.getItemRequestId());
        }else {
            itemRequestDetails = itemRequestDetailRepository.findAllByItemRequestNotComleted(false,request.getItemRequestId());
        }

        List<ItemRequestDetailModel> itemRequestDetailModelList = new ArrayList<>();
        itemRequestDetails.forEach(detail -> {
            ItemRequestDetailModel model = new ItemRequestDetailModel();
            model.setId(detail.getId());
            model.setItemDetailId(detail.getItemDetailId());
            model.setItemRequestId(detail.getItemRequestId());
            model.setQuantity(detail.getRemain());
            model.setRemain(detail.getRemain());
            model.setRequestedQuantity(detail.getQuantity());
            model.setUnitId(detail.getUnitId());
            model.setItemDetailText(detail.getItemDetail().getDetail());
            model.setUnitText(detail.getUnit().getName());
            model.setBaseQuantity(detail.getBaseQuantity());
            itemRequestDetailModelList.add(model);
        });

        return itemRequestDetailModelList;
    }
}
