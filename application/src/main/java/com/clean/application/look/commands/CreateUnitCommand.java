package com.clean.application.look.commands;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.look.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUnitCommand implements IRequest<Unit>{
    private short id;
    private String name;
}
