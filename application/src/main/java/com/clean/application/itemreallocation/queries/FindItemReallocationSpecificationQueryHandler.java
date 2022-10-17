package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationSpecificationModel;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.*;
import com.clean.persistence.itemreallocation.ItemReallocationRepository;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindItemReallocationSpecificationQueryHandler implements IRequestHandler<FindItemReallocationSpecificationQuery, List<ItemReallocationSpecificationModel>> {
    private ItemReallocationSpecificationRepository itemReallocationSpecificationRepository;
    private ItemReallocationRepository itemReallocationRepository;
    @Autowired
    FindItemReallocationSpecificationQueryHandler(
            ItemReallocationSpecificationRepository itemReallocationSpecificationRepository,ItemReallocationRepository itemReallocationRepository
    ){
        this.itemReallocationSpecificationRepository = itemReallocationSpecificationRepository;
        this.itemReallocationRepository = itemReallocationRepository;
    }
    @Override
    public List<ItemReallocationSpecificationModel> handle(FindItemReallocationSpecificationQuery request) {

        List<ItemReallocationSpecification> reallocatedList=itemReallocationSpecificationRepository.findReallocatedSpecificationByReallocationId(request.getItemReallocationId(),StatusMapped.REALLOCATION_DETAIL_REALLOCATED);
        ItemReallocation reallocation = itemReallocationRepository.findById(request.getItemReallocationId()).orElseThrow(()->new RuntimeException("راپور اعاده جنس دریافت نگردید!"));
        List<ItemReallocationSpecificationModel> itemReallocationSpeceficationModels = new ArrayList<>();
        reallocatedList.forEach(list->{
            ItemReallocationSpecificationModel model = new ItemReallocationSpecificationModel();
            model.setId(list.getId());
            model.setItemSpecificationId(list.getItemDistributedSpecification().getItemSpecificationId());
            model.setTagNumber(list.getItemDistributedSpecification().getItemSpecification().getTagNumber());
            model.setSerialNumber(list.getItemDistributedSpecification().getItemSpecification().getSerialNumber());
            model.setLocation(list.getItemDistributedSpecification().getItemSpecification().getLocation());
            model.setExpirationDate(list.getItemDistributedSpecification().getItemSpecification().getExpirationDate());
            model.setItemDetail(list.getItemReallocationDetail().getItemDistributionDetail().getItemDetail().getDetail());
            model.setBoardStatusId(list.getBoardStatusId());
            model.setPrice(list.getPrice());
            model.setMainPrice(list.getItemDistributedSpecification().getItemSpecification().getPrice());
            model.setItemReallocationId(reallocation.getId());
            model.setStageId(reallocation.getStageId());
            itemReallocationSpeceficationModels.add(model);
        });
        return itemReallocationSpeceficationModels;
    }
}
