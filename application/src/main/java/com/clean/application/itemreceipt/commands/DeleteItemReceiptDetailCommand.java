package com.clean.application.itemreceipt.commands;

import com.clean.application.itemreceipt.models.ItemReceiptDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeleteItemReceiptDetailCommand implements IRequest<Boolean> {
    private long id;

}
