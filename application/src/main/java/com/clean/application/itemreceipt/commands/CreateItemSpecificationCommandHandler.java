package com.clean.application.itemreceipt.commands;

import com.clean.application.itemreceipt.models.ItemSpecificationModel;
import com.clean.common.constant.FormPrefix;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Donor;
import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.domain.entity.stc.ItemSpecification;
import com.clean.persistence.donor.DonorRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateItemSpecificationCommandHandler implements IRequestHandler<CreateItemSpecificationCommand, ItemSpecificationModel> {
    private ItemSpecificationRepository repository;
    private ItemReceiptRepository itemReceiptRepository;
    private ItemRepository itemRepository;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    private StageRepository stageRepository;
    private DonorRepository donorRepository;
    @Autowired
    CreateItemSpecificationCommandHandler(ItemSpecificationRepository repository , ItemReceiptRepository itemReceiptRepository,ItemRepository itemRepository,ItemReceiptDetailRepository itemReceiptDetailRepository,StageRepository stageRepository, DonorRepository donorRepository){
        this.repository = repository;
        this.itemReceiptRepository = itemReceiptRepository;
        this.itemRepository = itemRepository;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
        this.stageRepository= stageRepository;
        this.donorRepository = donorRepository;
    }
    @Override
    public ItemSpecificationModel handle(CreateItemSpecificationCommand request) {

        //BRC:check it editable
        if(!stageRepository.findById(itemReceiptRepository.findById(itemReceiptDetailRepository.findById(request.getItemReceiptDetailId()).get().getItemReceiptId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل ویرایش نمیباشد!");
        }

        int itemDetailId = itemReceiptDetailRepository.findById(request.getItemReceiptDetailId()).get().getItemDetailId();
        boolean isConsumable= itemRepository.findItemByItemDetial(itemDetailId).getConsumable();
        if(isConsumable){
            throw new RuntimeException("برای اجناس مصرفی نمیتوانید جزئیات آنرا ثبت نمائید!");
        }
        ItemSpecification specification = repository.findById(request.getId()).orElse(null);
        if(specification == null){
            specification = new ItemSpecification();
            specification.setStatusId(StatusMapped.SPECIFICATION_WAREHOUSE);
            ItemSpecification itemSpecification= repository.findFirstByItemReceiptDetailIdOrderByTagNumberDesc(request.getItemReceiptDetailId());
            
            String tagNumber="";
            if(itemSpecification != null){
                tagNumber = itemSpecification.getTagNumber();
            }

            String newTagNumber="";
            String seqPattern = "000";

            if(tagNumber.isEmpty()){
                ItemReceipt receipt = itemReceiptRepository.findByItemReceiptDetailId(request.getItemReceiptDetailId());
                Donor donor = donorRepository.findById(receipt.getDonorId()).get();
                seqPattern =  seqPattern.replaceFirst(".{"+1+"}$", String.valueOf(1));
                newTagNumber =donor.getAbbreviation()+"-"+ receipt.getCode()+"-"+seqPattern;
                newTagNumber = newTagNumber.replace(FormPrefix.M7+"-", "");
            }else{
                String split[]= tagNumber.split("-",6);
                String sequence =String.valueOf((Integer.parseInt(split[split.length-1])  + 1));
                seqPattern = seqPattern.replaceFirst(".{" + sequence.length() + "}$", sequence);
                
                newTagNumber = Arrays.toString(Arrays.copyOf(split, split.length-1));
                newTagNumber = newTagNumber.replace(FormPrefix.M7+"-", "").replace("[", "").replace("]", "").replace(",", "-").replace(" ", "");
                newTagNumber = newTagNumber+"-"+seqPattern;
            }
            specification.setTagNumber(newTagNumber);
        }
        specification.setExpirationDate(request.getExpirationDate());
        specification.setSerialNumber(request.getSerialNumber());
        specification.setLocation(request.getLocation());
        specification.setItemReceiptDetailId(request.getItemReceiptDetailId());
        double price = itemReceiptDetailRepository.findById(request.getItemReceiptDetailId()).get().getPrice();
        specification.setPrice(price);
        repository.save(specification);
        return ItemSpecificationModel.map(specification);
    }
}
