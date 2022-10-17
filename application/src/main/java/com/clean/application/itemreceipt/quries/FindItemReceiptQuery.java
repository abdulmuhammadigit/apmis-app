package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemReceiptModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindItemReceiptQuery implements IRequest<ItemReceiptModel> {
    private long id;
}
