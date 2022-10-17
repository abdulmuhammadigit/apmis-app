package com.clean.application.itemrequest.commands;

import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteItemRequestDetailCommand implements IRequest<Boolean> {
    private long id;
}
