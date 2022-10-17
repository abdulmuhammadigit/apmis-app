package com.clean.application.look.quries;

import com.clean.domain.entity.look.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.clean.common.mediator.core.IRequest;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchStatusQuery implements IRequest<List<Status>> {
    private String dbObject;
    private String category;
}
