package com.clean.application.itemreallocation.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReallocationApproveCommand implements IRequest<Boolean> {
   private long itemReallocationId;
}
