package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationModel;
import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.utils.EntityIds;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.stc.ItemReallocation;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CreateItemReallocationCommandHandler implements IRequestHandler<CreateItemReallocationCommand, ItemReallocationModel> {
    private ItemReallocationRepository itemReallocationRepository;
    private EmployeeRepository employeeRepository;
    private IMediator mediator;

    @Autowired
    CreateItemReallocationCommandHandler(
            ItemReallocationRepository itemReallocationRepository,
            ItemReallocationDetailRepository itemReallocationDetailRepository,
            EntityManager manager,
            EmployeeRepository employeeRepository,
            IMediator mediator
    )
    {
        this.itemReallocationRepository = itemReallocationRepository;
        this.employeeRepository = employeeRepository;
        this.mediator = mediator;
    }
    @Override
    public ItemReallocationModel handle(CreateItemReallocationCommand request) {

        //save or update item reallocation entity
        ItemReallocation itemReallocation = itemReallocationRepository.findById(request.getId()).orElse(null);
        if(itemReallocation == null){
            itemReallocation = new ItemReallocation();
            itemReallocation.setCode(request.getCode());
        }
        //itemReallocation.setDocumentNumber(request.getDocumentNumber());
        itemReallocation.setDate(request.getDate());
        itemReallocation.setDescription(request.getDescription());
        itemReallocation = itemReallocationRepository.save(itemReallocation);
        ItemReallocationModel itemReallocationModel= ItemReallocationModel.map(itemReallocation);
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()-> new RuntimeException("کارمند دریافت نگردید!"));
        itemReallocationModel.setEmployeeText(employee.getName()+" " + employee.getLastName()+" فرزند " + employee.getFatherName());
        itemReallocationModel.setEmployeeId(employee.getId());
        // initial process tracking
        if(request.getId() == 0){
            InitialHistoryCommandResult result = mediator.send(
                    CreateInitialHistoryCommand.builder()
                    .recordId(itemReallocation.getId())
                    .entityId(EntityIds.ItemReallocation)
                    .build()
            );

            itemReallocation.setStageId(result.getToStage().getId());
            itemReallocationRepository.save(itemReallocation);
            itemReallocationModel.setStageId(result.getToStage().getId());
        }

        return  itemReallocationModel;
    }
}
