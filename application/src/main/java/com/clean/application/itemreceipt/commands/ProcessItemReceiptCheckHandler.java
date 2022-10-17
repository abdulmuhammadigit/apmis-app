package com.clean.application.itemreceipt.commands;

import java.util.List;

import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessItemReceiptCheckHandler implements IRequestHandler<ProcessItemReceipCheck,Boolean> {
    private ItemReceiptRepository itemReceiptRepository;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    private ItemSpecificationRepository itemSpecificationRepository;
    private ItemRepository itemRepository;
    @Autowired
    public ProcessItemReceiptCheckHandler(ItemReceiptRepository itemReceiptRepository , ItemReceiptDetailRepository itemReceiptDetailRepository,ItemSpecificationRepository itemSpecificationRepository,ItemRepository itemRepository) {
        this.itemReceiptRepository=itemReceiptRepository;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
        this.itemSpecificationRepository = itemSpecificationRepository;
        this.itemRepository = itemRepository;
    }
    @Override
    public Boolean handle(ProcessItemReceipCheck request) {
        if(request.getBusinessRule().equals(BusinessRuleMapped.ItemReceiptDetailCheck)){
            List<ItemReceiptDetail> detailList =  itemReceiptDetailRepository.findByItemReceiptId(request.getRecordId());
            detailList.forEach(e->{
                boolean consumable = itemRepository.findItemByItemDetial(e.getItemDetailId()).getConsumable();
                if(!consumable){
                    if(itemSpecificationRepository.findAllByItemReceipt(request.getRecordId()).size() < e.getBaseQuantity() ){
                        throw new RuntimeException("برای تمام اجناس جزئیات آنرا ثبت نمائید!");
                    }
                }
            });

            if(!itemReceiptDetailRepository.existsByItemReceiptId(request.getRecordId())){
                throw new RuntimeException("اجناس برای رسید وجود ندارد!");
            }
        }
        if(request.getBusinessRule().equals(BusinessRuleMapped.ItemReceiptApprove)){
                ItemReceipt receipt= itemReceiptRepository.findById(request.getRecordId()).get();
                receipt.setStatusId(StatusMapped.RECEIPT_USABLE);
                receipt.setStageId(request.getStageId());
                itemReceiptRepository.save(receipt);
                return true;
        }

        // change status
        ItemReceipt receipt= itemReceiptRepository.findById(request.getRecordId()).get();
                receipt.setStageId(request.getStageId());
                itemReceiptRepository.save(receipt);
        return true;
    }
    
}
