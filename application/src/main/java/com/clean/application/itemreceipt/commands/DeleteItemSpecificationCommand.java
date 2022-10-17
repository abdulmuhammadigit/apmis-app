package com.clean.application.itemreceipt.commands;

import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteItemSpecificationCommand implements IRequest<Boolean> {
    private long id;
}
