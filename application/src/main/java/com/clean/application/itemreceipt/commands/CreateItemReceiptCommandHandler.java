package com.clean.application.itemreceipt.commands;

import com.clean.application.itemreceipt.models.ItemReceiptModel;
import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.utils.EntityIds;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateItemReceiptCommandHandler implements IRequestHandler<CreateItemReceiptCommand, ItemReceiptModel> {

    private ItemReceiptRepository repository;
    private IMediator mediator;
    private StageRepository stageRepository;

    @Autowired
    public CreateItemReceiptCommandHandler(ItemReceiptRepository repository,IMediator mediator,StageRepository stageRepository){
        this.repository = repository;
        this.mediator = mediator;
        this.stageRepository = stageRepository;
    }

    @Override
    public ItemReceiptModel handle(CreateItemReceiptCommand request) {

        //save or update item receipt
        ItemReceipt itemReceipt = repository.findById(request.getId()).orElse(null);
        if (itemReceipt == null) {
            itemReceipt = new ItemReceipt();
            itemReceipt.setStatusId(StatusMapped.RECEIPT_UNERPROCESS);
            itemReceipt.setCode(request.getCode());
        }else{
            //check for stage is editable
            if(!stageRepository.findById(itemReceipt.getStageId()).get().isEditable()){
                throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
            }
        }

        itemReceipt.setBillNumber(request.getBillNumber());
        itemReceipt.setDescription(request.getDescription());
        //itemReceipt.setDocumentDate(request.getDocumentDate());
        //itemReceipt.setDocumentNumber(request.getDocumentNumber());
        itemReceipt.setItemReceiptTypeId(request.getItemReceiptTypeId());
        itemReceipt.setStockKeeperId(request.getStockKeeperId());
        itemReceipt.setReceiveDate(request.getReceiveDate());
        itemReceipt.setDonorId(request.getDonorId());
        itemReceipt.setM16Number(request.getM16Number());
        itemReceipt.setM3Number(request.getM3Number());
        itemReceipt = repository.saveAndFlush(itemReceipt);

        // initial process tracking
        if(request.getId() == 0){
            InitialHistoryCommandResult result = mediator.send(
                    CreateInitialHistoryCommand.builder()
                    .recordId(itemReceipt.getId())
                    .entityId(EntityIds.ItemReceipt)
                    .build()
            );

            itemReceipt.setStageId(result.getToStage().getId());

            repository.save(itemReceipt);
        }
        
        return ItemReceiptModel.map(itemReceipt);
    }
}
