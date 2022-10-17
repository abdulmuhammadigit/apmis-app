package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationDetailModel;
import com.clean.application.itemreallocation.models.ItemReallocationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.domain.entity.stc.ItemReallocation;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationRepository;
import com.clean.persistence.look.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class FindItemReallocationQueryHandler implements IRequestHandler<FindItemReallocationQuery, ItemReallocationModel> {
   private ItemReallocationRepository itemReallocationRepository;
   private ItemReallocationDetailRepository itemReallocationDetailRepository;
   private EmployeeRepository employeeRepository;
   private ItemDistributionDetailRepository itemDistributionDetailRepository;
   private StatusRepository statusRepository;
   private ItemDetailRepository itemDetailRepository;
   @Autowired
    FindItemReallocationQueryHandler(
            ItemReallocationRepository itemReallocationRepository,
            ItemReallocationDetailRepository itemReallocationDetailRepository,
            EmployeeRepository employeeRepository,
            ItemDistributionDetailRepository itemDistributionDetailRepository,
            StatusRepository statusRepository,
            ItemDetailRepository itemDetailRepository
   ){
       this.itemReallocationRepository = itemReallocationRepository;
       this.itemReallocationDetailRepository = itemReallocationDetailRepository;
       this.employeeRepository = employeeRepository;
       this.itemDistributionDetailRepository = itemDistributionDetailRepository;
       this.statusRepository = statusRepository;
       this.itemDetailRepository = itemDetailRepository;
   }
    @Override
    public ItemReallocationModel handle(FindItemReallocationQuery request) {
        ItemReallocation itemReallocation = itemReallocationRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("جنس برای اعاده دریافت نگردید."));
        
        List<ItemReallocationDetail> itemReallocationDetail = itemReallocationDetailRepository.findAllByItemReallocationId(itemReallocation.getId());
        List<ItemReallocationDetailModel> detailModels = itemReallocationDetail.stream().map(ItemReallocationDetailModel::map).collect(Collectors.toList());

        detailModels.forEach(e->{
           ItemDistributionDetail itemDistributionDetail = itemDistributionDetailRepository.findById(e.getItemDistributionDetailId()).get();
            e.setQuantity(itemDistributionDetail.getQuantity());
            e.setRequestedQuantity(itemDistributionDetail.getItemRequestDetail().getBaseQuantity());
            e.setRemain((short)(itemDistributionDetail.getQuantity() - e.getReallocationQuantity()));
            ItemDetail itemDetail = itemDetailRepository.findById(itemDistributionDetail.getItemDetailId()).get();
            e.setItemDetailText(itemDetail.getDetail());
        });
        ItemReallocationModel model = ItemReallocationModel.map(itemReallocation);
        model.setItemReallocationDetailModels(detailModels);
        Employee employee = employeeRepository.findByEmployeeId(model.getId());
        model.setEmployeeText(employee.getName() + " " + employee.getLastName() + " فرزند " + employee.getFatherName());
        return model;
    }
}
