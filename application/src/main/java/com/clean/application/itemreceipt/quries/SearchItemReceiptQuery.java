package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemReceiptSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;

@Data
public class SearchItemReceiptQuery implements IRequest<List<ItemReceiptSearchModel>> {
    private String code;
    private String billNumber;
    private String documentNumber;
}
