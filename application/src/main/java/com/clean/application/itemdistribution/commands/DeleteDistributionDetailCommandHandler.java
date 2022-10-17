package com.clean.application.itemdistribution.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemdistribution.ItemDistributionDetailRepository;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDistributionDetailCommandHandler implements IRequestHandler<DeleteDistributionDetailCommand,Boolean> {
    private ItemDistributionDetailRepository repository;
    private StageRepository stageRepository;
    private ItemDistributionRepository itemDistributionRepository;
    @Autowired
    public DeleteDistributionDetailCommandHandler(ItemDistributionDetailRepository repository,StageRepository stageRepository, ItemDistributionRepository itemDistributionRepository){
        this.repository = repository;
        this.stageRepository = stageRepository;
        this.itemDistributionRepository = itemDistributionRepository;
    }
    @Override
    public Boolean handle(DeleteDistributionDetailCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemDistributionRepository.findById(repository.findById(request.getId()).get().getItemDistributionId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل حذف نمیباشد!");
        }

        if (repository.findById(request.getId()).get().isDistributed()) {
            throw new RuntimeException("شما نمیتوانید جنس توزیع شده را حذف نمائید!");
        }
        repository.deleteById(request.getId());
        return true;
    }
    
}
