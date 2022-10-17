package com.clean.application.itemreceipt.commands;

import com.clean.common.mediator.core.IRequestHandler;

import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemReceiptDetailCommandHandler implements IRequestHandler<DeleteItemReceiptDetailCommand, Boolean> {

    private ItemReceiptDetailRepository repoDetail;
    private ItemSpecificationRepository itemSpecificationRepository;
    private StageRepository stageRepository;
    private ItemReceiptRepository itemReceiptRepository;

    @Autowired
    public DeleteItemReceiptDetailCommandHandler(ItemReceiptDetailRepository repodetail, ItemSpecificationRepository itemSpecificationRepository,StageRepository stageRepository,ItemReceiptRepository itemReceiptRepository) {
        this.repoDetail = repodetail;
        this.itemSpecificationRepository = itemSpecificationRepository;
        this.stageRepository = stageRepository;
        this.itemReceiptRepository = itemReceiptRepository;
    }

    @Override
    public Boolean handle(DeleteItemReceiptDetailCommand request) {

        //BRC:check it editable

        if(!stageRepository.findById(itemReceiptRepository.findById(repoDetail.findById(request.getId()).get().getItemReceiptId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل حذف نمیباشد!");
        }
        if(itemSpecificationRepository.existsByItemReceiptDetailId(request.getId())){
            throw new RuntimeException("برای جنس مذکور جزئیات آن درج گردیده است اولاً آنها را حذف نمائید");
        }
        repoDetail.deleteById(request.getId());
        return true;
    }
}
