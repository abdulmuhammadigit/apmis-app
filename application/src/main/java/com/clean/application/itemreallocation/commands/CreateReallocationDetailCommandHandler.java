package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationDetailModel;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Status;
import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.look.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateReallocationDetailCommandHandler implements IRequestHandler<CreateReallocationDetailCommand, ItemReallocationDetailModel> {
    private ItemReallocationDetailRepository itemReallocationDetailRepository;
    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private EmployeeRepository employeeRepository;
    private StatusRepository statusRepository;

    @Autowired
    CreateReallocationDetailCommandHandler(
            ItemReallocationDetailRepository itemReallocationDetailRepository,
            ItemDistributionDetailRepository itemDistributionDetailRepository,
            ItemDetailRepository itemDetailRepository,
            EmployeeRepository employeeRepository,
            StatusRepository statusRepository
    ) {
        this.itemReallocationDetailRepository = itemReallocationDetailRepository;
        this.itemDistributionDetailRepository = itemDistributionDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.employeeRepository = employeeRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public ItemReallocationDetailModel handle(CreateReallocationDetailCommand request) {
        //BRC: check for quantity;
        
        // save or update item reallocation detail entity
        ItemReallocationDetail itemReallocationDetail = itemReallocationDetailRepository.findById(request.getId()).orElse(null);
        if (itemReallocationDetail == null) {
            itemReallocationDetail = new ItemReallocationDetail();
            itemReallocationDetail.setConfirmed(false);
            itemReallocationDetail.setStatusId(StatusMapped.REALLOCATION_DETAIL_UNDER_REALLOCATION);

            if(request.getReallocationQuantity() > request.getRemain()){
                throw new RuntimeException("مقدار قابل اعاده نمیتواند بزرگتر از مقدار باقیمانده باشد!");
            }
        }else{
            if(request.getReallocationQuantity() > request.getQuantity()){
                throw new RuntimeException("مقدار قابل اعاده نمیتواند بزرگتر از مقدار توزیع شده باشد!");
            } 
        }
        itemReallocationDetail.setItemReallocationId(request.getItemReallocationId());
        itemReallocationDetail.setItemDistributionDetailId(request.getItemDistributionDetailId());
        itemReallocationDetail.setReallocationQuantity(request.getReallocationQuantity());
        itemReallocationDetail.setDescription(request.getDescription());

        itemReallocationDetail = itemReallocationDetailRepository.save(itemReallocationDetail);
        ItemReallocationDetailModel detailModel= ItemReallocationDetailModel.map(itemReallocationDetail);
        ItemDistributionDetail itemDistributionDetail = itemDistributionDetailRepository.findById(itemReallocationDetail.getItemDistributionDetailId()).get();
        detailModel.setQuantity(itemDistributionDetail.getQuantity());
        detailModel.setRequestedQuantity(itemDistributionDetail.getItemRequestDetail().getBaseQuantity());
        detailModel.setRemain((short)(itemDistributionDetail.getQuantity() - itemReallocationDetail.getReallocationQuantity()));
        ItemDetail itemDetail = itemDetailRepository.findById(itemDistributionDetail.getItemDetailId()).get();
        detailModel.setItemDetailText(itemDetail.getDetail());
//        Employee employee = employeeRepository.findById(itemReallocationDetail.getToEmployeeId()).get();
//        detailModel.setToEmployeeText(employee.getName());
//
//        Status status = statusRepository.findById(itemReallocationDetail.getStatusId()).get();
//        detailModel.setStatusText(status.getName());

        return detailModel;
    }
}
