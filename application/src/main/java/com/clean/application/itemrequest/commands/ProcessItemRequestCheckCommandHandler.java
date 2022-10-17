package com.clean.application.itemrequest.commands;

import javax.transaction.Transactional;

import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemRequest;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProcessItemRequestCheckCommandHandler implements IRequestHandler<ProcessItemRequestCheckCommand,Boolean> {
    private ItemRequestDetailRepository itemRequestDetailRepository;
    private ItemRequestRepository itemRequestRepository;
    @Autowired
    public ProcessItemRequestCheckCommandHandler(ItemRequestDetailRepository itemRequestDetailRepository,ItemRequestRepository itemRequestRepository) {
        this.itemRequestDetailRepository = itemRequestDetailRepository;
        this.itemRequestRepository = itemRequestRepository;
    }
    @Override
    public Boolean handle(ProcessItemRequestCheckCommand request) {
        if(request.getBusinessRule().equals(BusinessRuleMapped.ItemRequestDetailCheck)){
            if(!itemRequestDetailRepository.existsByItemRequestId(request.getRecordId())){
                throw new RuntimeException("اجناس برای درخواست وجود ندارد!");
            }
        }

        ItemRequest itemRequest= itemRequestRepository.findById(request.getRecordId()).get();
                itemRequest.setStageId(request.getStageId());
                itemRequestRepository.save(itemRequest);

        return true;
    }
    
}
