package com.clean.application.processtracking.queries;

import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindConnectionQuery implements IRequest<ConnectionModel> {
    private int id;
}
