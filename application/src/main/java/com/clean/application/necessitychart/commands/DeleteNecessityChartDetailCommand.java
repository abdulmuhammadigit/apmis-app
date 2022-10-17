package com.clean.application.necessitychart.commands;

import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteNecessityChartDetailCommand implements IRequest<Boolean> {
    private long id;
}
