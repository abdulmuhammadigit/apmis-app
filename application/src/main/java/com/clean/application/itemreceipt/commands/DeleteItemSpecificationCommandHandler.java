package com.clean.application.itemreceipt.commands;

import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemSpecificationCommandHandler implements IRequestHandler<DeleteItemSpecificationCommand,Boolean>{
    private ItemSpecificationRepository itemSpecificationRepository;
    private StageRepository stageRepository;
    private ItemReceiptRepository itemReceiptRepository;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    @Autowired
    public DeleteItemSpecificationCommandHandler(ItemSpecificationRepository itemSpecificationRepository,StageRepository stageRepository,ItemReceiptRepository itemReceiptRepository,ItemReceiptDetailRepository itemReceiptDetailRepository){
        this.itemSpecificationRepository = itemSpecificationRepository;
        this.stageRepository = stageRepository;
        this.itemReceiptRepository = itemReceiptRepository;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
    }
    @Override
    public Boolean handle(DeleteItemSpecificationCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemReceiptRepository.findById(itemReceiptDetailRepository.findById(itemSpecificationRepository.findById(request.getId()).get().getItemReceiptDetailId()).get().getItemReceiptId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل حذف نمیباشد!");
        }

        if(itemSpecificationRepository.findById(request.getId()).get().getStatusId() != StatusMapped.SPECIFICATION_WAREHOUSE){
            throw new RuntimeException("جنس مذکور قابلیت حذف را ندارد چون در گدام موجود نیست!");
        }
        itemSpecificationRepository.deleteById(request.getId());
        return true;
    }
    
}
