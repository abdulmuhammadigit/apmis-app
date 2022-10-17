package com.clean.application.itemreallocation.models;

import com.clean.domain.entity.stc.ItemReallocationDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReallocationDetailModel {
    private long id;
    private long itemReallocationId;
    private long itemDistributionDetailId;
    private short reallocationQuantity;
    private int statusId;
    private String description;
    //private int toEmployeeId;
    private boolean confirmed;
    private String itemDetailText;
    private String statusText;
    private short quantity;
    private short requestedQuantity;
    private short remain;
    //private String toEmployeeText;

    public static ItemReallocationDetailModel map(ItemReallocationDetail itemReallocationDetail){
        ItemReallocationDetailModel model = new ItemReallocationDetailModel();
        model.setId(itemReallocationDetail.getId());
        model.setItemReallocationId(itemReallocationDetail.getItemReallocationId());
        model.setItemDistributionDetailId(itemReallocationDetail.getItemDistributionDetailId());
        model.setReallocationQuantity(itemReallocationDetail.getReallocationQuantity());
        model.setStatusId(itemReallocationDetail.getStatusId());
        model.setDescription(itemReallocationDetail.getDescription());
       // model.setToEmployeeId(itemReallocationDetail.getToEmployeeId());
        return model;
    }
}
