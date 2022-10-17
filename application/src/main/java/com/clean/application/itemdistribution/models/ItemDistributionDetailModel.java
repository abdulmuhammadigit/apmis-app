package com.clean.application.itemdistribution.models;

import com.clean.domain.entity.stc.ItemDistributionDetail;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemDistributionDetailModel {

    private long id;
    private long itemDistributionId;
    private long itemRequestDetailId;
    private int itemDetailId;
    private short quantity;
    private boolean distributed;
    private String itemDetailText;
    private String unitText;
    private short remain;
    private short requestedQuantity;
    private short reallocatedQuantity;
    public static ItemDistributionDetailModel map(ItemDistributionDetail itemDistributionDetail)
    {
        ItemDistributionDetailModel model = new ItemDistributionDetailModel();
        model.setId(itemDistributionDetail.getId());
        model.setItemDistributionId(itemDistributionDetail.getItemDistributionId());
        model.setItemRequestDetailId(itemDistributionDetail.getItemRequestDetailId());
        model.setItemDetailId(itemDistributionDetail.getItemDetailId());
        model.setQuantity(itemDistributionDetail.getQuantity());
        model.setDistributed(itemDistributionDetail.isDistributed());
        model.setReallocatedQuantity(itemDistributionDetail.getReallocatedQuantity());
        return model;
    }

}
