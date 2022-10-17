package com.clean.application.itemreceipt.quries;

import java.util.List;

import com.clean.application.itemreceipt.models.ItemReceipItemDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindItemDetailListQuery implements IRequest<List<ItemReceipItemDetailModel>> {
    private long itemReceiptId;
}
