package com.clean.application.itemdistribution.models;

import com.clean.domain.entity.stc.ItemDistributed;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ItemDistributedModel {
    private long itemDistributedId;
    private long itemReceiptDetailId;
    private short quantity;
    private long itemSpecificationId;
}
