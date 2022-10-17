package com.clean.application.itemdistribution.commands;

import com.clean.application.itemdistribution.models.ItemDistributionModel;
import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.utils.EntityIds;
import com.clean.common.constant.StageMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.*;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateItemDistributionCommandHandler implements IRequestHandler<CreateItemDistributionCommand, ItemDistributionModel> {

    private ItemDistributionRepository itemDistributionRepository;
    private ItemRequestRepository itemRequestRepository;
    private IMediator mediator;
    private StageRepository stageRepository;
    @Autowired
    public CreateItemDistributionCommandHandler(ItemDistributionRepository itemDistributionRepository,
                                                ItemRequestRepository itemRequestRepository,
                                                IMediator mediator,
                                                StageRepository stageRepository
    ) {
        this.itemDistributionRepository = itemDistributionRepository;
        this.itemRequestRepository = itemRequestRepository;
        this.mediator = mediator;
        this.stageRepository = stageRepository;
    }

    @Override
    public ItemDistributionModel handle(CreateItemDistributionCommand request) {
        // save or update item distribution entity
        ItemDistribution itemDistribution = itemDistributionRepository.findById(request.getId()).orElse(null);
        if (itemDistribution == null) {
            itemDistribution = new ItemDistribution();
            itemDistribution.setCode(request.getCode());
        }
        else{
            //check for stage is editable
            if(!stageRepository.findById(itemDistribution.getStageId()).get().isEditable()){
                throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
            }
        }

        itemDistribution.setDocumentNumber(request.getDocumentNumber());
        itemDistribution.setDate(request.getDate());
        itemDistribution.setItemRequestId(request.getItemRequestId());
        itemDistribution.setDescription(request.getDescription());
        itemDistribution = itemDistributionRepository.save(itemDistribution);

         // initial process tracking
         if(request.getId() == 0){
            InitialHistoryCommandResult result = mediator.send(
                    CreateInitialHistoryCommand.builder()
                    .recordId(itemDistribution.getId())
                    .entityId(EntityIds.ItemDistribution)
                    .build()
            );

            itemDistribution.setStageId(result.getToStage().getId());

            itemDistributionRepository.save(itemDistribution);
        }

        // map data
        ItemDistributionModel model = ItemDistributionModel.map(itemDistribution);
        model.setItemRequestCode(itemRequestRepository.findById(itemDistribution.getItemRequestId()).get().getCode());
        return model;
    }
}


