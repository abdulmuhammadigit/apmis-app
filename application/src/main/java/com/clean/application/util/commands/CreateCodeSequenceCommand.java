package com.clean.application.util.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCodeSequenceCommand implements IRequest<String>{
    private String formPrefix;
}
