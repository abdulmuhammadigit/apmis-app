package com.clean.application.itemrequest.models;

import com.clean.domain.entity.stc.ItemRequestDetail;
import lombok.Data;

@Data
public class ItemRequestDetailModel {
    private long id;
    private int itemDetailId;
    private long itemRequestId;
    private short quantity;
    private short remain;
    private short unitId;
    private String description;
    private short baseQuantity;
    private String itemDetailText;
    private String unitText;
    private short requestedQuantity;
    public static ItemRequestDetailModel map(ItemRequestDetail itemRequestDetail)
    {
        ItemRequestDetailModel model = new ItemRequestDetailModel();
        model.setId(itemRequestDetail.getId());
        model.setItemDetailId(itemRequestDetail.getItemDetailId());
        model.setItemRequestId(itemRequestDetail.getItemRequestId());
        model.setQuantity(itemRequestDetail.getQuantity());
        model.setRemain(itemRequestDetail.getRemain());
        model.setUnitId(itemRequestDetail.getUnitId());
        model.setRequestedQuantity(itemRequestDetail.getQuantity());
        model.setDescription(itemRequestDetail.getDescription());
        model.setBaseQuantity(itemRequestDetail.getBaseQuantity());
        return model;
    }
}
