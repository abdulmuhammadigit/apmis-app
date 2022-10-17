package com.clean.application.itemrequest.commands;

import com.clean.application.itemrequest.models.ItemRequestModel;
import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.utils.EntityIds;
import com.clean.common.constant.StageMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.stc.ItemRequest;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateItemRequestCommandHandler implements IRequestHandler<CreateItemRequestCommand, ItemRequestModel> {
    private ItemRequestRepository itemRequestRepository;
    private EmployeeRepository employeeRepository;
    private IMediator mediator;
    private StageRepository stageRepository;
    @Autowired
    CreateItemRequestCommandHandler(ItemRequestRepository ItemRequestRepository,
                                    EmployeeRepository employeeRepository,
                                    IMediator mediator,
                                    StageRepository stageRepository
    ) {
        this.itemRequestRepository = ItemRequestRepository;
        this.employeeRepository = employeeRepository;
        this.mediator = mediator;
        this.stageRepository = stageRepository;
    }

    @Override
    public ItemRequestModel handle(CreateItemRequestCommand request) {
        //save or update item request entity
        ItemRequest itemRequest = itemRequestRepository.findById(request.getId()).orElse(null);
        if(itemRequest == null){
            itemRequest = new ItemRequest();

            itemRequest.setCode(request.getCode());
            itemRequest.setCompleted(false);
        }else{
            //check for stage is editable
            if(!stageRepository.findById(itemRequest.getStageId()).get().isEditable()){
                throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
            }
        }

        itemRequest.setDate(request.getDate());
        itemRequest.setDescription(request.getDescription());
        itemRequest.setDocumentNumber(request.getDocumentNumber());
        itemRequest.setEmployeeId(request.getEmployeeId());
        itemRequest.setDepartment(request.getDepartment());
        itemRequest = itemRequestRepository.save(itemRequest);

        // initial process tracking
        if(request.getId() == 0){
            InitialHistoryCommandResult result = mediator.send(
                    CreateInitialHistoryCommand.builder()
                    .recordId(itemRequest.getId())
                    .entityId(EntityIds.ItemRequest)
                    .build()
            );

            itemRequest.setStageId(result.getToStage().getId());

            itemRequestRepository.save(itemRequest);
        }
        
        ItemRequestModel requestModel= ItemRequestModel.map(itemRequest);
        Employee employee = employeeRepository.findById(requestModel.getEmployeeId()).get();
        requestModel.setEmployeeText(employee.getName() + " '" +employee.getLastName() + "' فرزند " + employee.getFatherName());
        return requestModel;
    }
}
