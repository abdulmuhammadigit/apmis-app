package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequestDetailModel;
import com.clean.application.itemrequest.models.ItemRequestModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.stc.ItemRequest;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.hr.OrgUnitRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.look.OrgUnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class FindItemRequestQueryHandler implements IRequestHandler<FindItemRequestQuery, ItemRequestModel> {
    private ItemRequestRepository itemRequestRepository;
    private ItemRequestDetailRepository itemRequestDetailRepository;
    private ItemDetailRepository itemDetailRepository;
    private EmployeeRepository employeeRepository;
    private ItemRepository itemRepository;

    @Autowired
    FindItemRequestQueryHandler(ItemRequestRepository itemRequestRepository,
                                ItemRequestDetailRepository itemRequestDetailRepository,
                                ItemDetailRepository itemDetailRepository,
                                EmployeeRepository employeeRepository,
                                OrgUnitRepository orgUnitRepository,
                                OrgUnitTypeRepository orgUnitTypeRepository,
                                ItemRepository itemRepository){
        this.itemRequestRepository = itemRequestRepository;
        this.itemRequestDetailRepository = itemRequestDetailRepository;
        this.itemDetailRepository = itemDetailRepository;
        this.employeeRepository= employeeRepository;
        this.itemRepository = itemRepository;
    }
    @Override
    public ItemRequestModel handle(FindItemRequestQuery request) {
        ItemRequest itemRequest=itemRequestRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("فورم درخواست دریافت نگردید!"));
        List<ItemRequestDetail> details = itemRequestDetailRepository.findAllByItemRequestId(itemRequest.getId());
        List<ItemRequestDetailModel> itemRequestDetailModels= details.stream().map(ItemRequestDetailModel::map).collect(Collectors.toList());
        itemRequestDetailModels.stream().forEach(e -> {
            e.setItemDetailText(itemDetailRepository.findById(e.getItemDetailId()).get().getDetail());
            e.setUnitText(itemRepository.findItemByItemDetial(e.getItemDetailId()).getUnit().getName());
        });
        
        ItemRequestModel model = ItemRequestModel.map(itemRequest);
        model.setDetailModelList(itemRequestDetailModels);
        Employee employee = employeeRepository.findById(model.getEmployeeId()).get();
        model.setEmployeeText(employee.getName() + " '" +employee.getLastName() + "' فرزند " + employee.getFatherName());
        model.setOrgUnitText(employee.getOrgUnit().getName());
        return model;

    }
}
