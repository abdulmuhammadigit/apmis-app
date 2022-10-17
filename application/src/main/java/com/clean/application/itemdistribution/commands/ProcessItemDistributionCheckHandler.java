package com.clean.application.itemdistribution.commands;

import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemDistribution;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessItemDistributionCheckHandler implements IRequestHandler<ProcessItemDistributionCheck,Boolean> {
    private ItemDistributionRepository itemDistributionRepository;
    private ItemDistributionDetailRepository itemDistributionDetailRepository;
    private IMediator mediator;
    @Autowired
    public ProcessItemDistributionCheckHandler(ItemDistributionRepository itemDistributionRepository,IMediator mediator,ItemDistributionDetailRepository itemDistributionDetailRepository) {
        this.itemDistributionRepository = itemDistributionRepository;
        this.mediator = mediator;
        this.itemDistributionDetailRepository = itemDistributionDetailRepository;
    }
    @Override
    public Boolean handle(ProcessItemDistributionCheck request) {
        if(request.getBusinessRule().equals(BusinessRuleMapped.ItemDistributionDetailCheck)){
            
            if(itemDistributionDetailRepository.existsByItemDistributionId(request.getRecordId())){
                mediator.send(CreateDistributionApproveCommand.builder()
                    .itemDistributionId(request.getRecordId())
                    .build());
            }else{
                throw new RuntimeException("اجناس برای توزیع انتخاب نگردیده اند!");
            }
        }

        // change stage
        ItemDistribution itemDistribution= itemDistributionRepository.findById(request.getRecordId()).get();
                itemDistribution.setStageId(request.getStageId());
                itemDistributionRepository.save(itemDistribution);
        return true;
    }
    
}
