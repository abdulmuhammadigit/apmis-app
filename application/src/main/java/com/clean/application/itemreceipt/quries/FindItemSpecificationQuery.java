package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemSpecificationModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;
@Data
public class FindItemSpecificationQuery implements IRequest<List<ItemSpecificationModel>> {
    private long itemReceiptDetailId;
}
