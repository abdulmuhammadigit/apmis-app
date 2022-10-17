package com.clean.application.itemreceipt.models;

import com.clean.domain.entity.stc.ItemReceipt;
import lombok.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReceiptModel  {
        private long id;
        private String code;
        private String documentNumber;
        private Date documentDate;
        private String itemReceiptTypeName;
        private short itemReceiptTypeId;
        private Date receiveDate;
        private String billNumber;
        private String stockKeeperName;
        private short stockKeeperId;
        private String description;
        private int stageId;
        private int donorId;
        private String m16Number;
        private String m3Number;
        private String stockKeeperLastname;
        private String stockKeeperFatherName;

        private List<ItemReceiptDetailModel> itemDetailList;
        public static ItemReceiptModel map(ItemReceipt itemReceiptM7)

        {
                ItemReceiptModel model = new ItemReceiptModel();
                model.setId(itemReceiptM7.getId());
                model.setCode(itemReceiptM7.getCode());
                model.setDocumentNumber(itemReceiptM7.getDocumentNumber());
                model.setDocumentDate(itemReceiptM7.getDocumentDate());
                model.setItemReceiptTypeId(itemReceiptM7.getItemReceiptTypeId());
                model.setReceiveDate(itemReceiptM7.getReceiveDate());
                model.setBillNumber(itemReceiptM7.getBillNumber());
                model.setStockKeeperId(itemReceiptM7.getStockKeeperId());
                model.setDescription(itemReceiptM7.getDescription());
                model.setStageId(itemReceiptM7.getStageId());
                model.setDonorId(itemReceiptM7.getDonorId());
                model.setM16Number(itemReceiptM7.getM16Number());
                model.setM3Number(itemReceiptM7.getM3Number());
                return model;
        }
}
