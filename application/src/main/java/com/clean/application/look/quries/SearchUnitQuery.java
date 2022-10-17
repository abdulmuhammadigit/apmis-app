package com.clean.application.look.quries;

import com.clean.domain.entity.look.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.clean.common.mediator.core.IRequest;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUnitQuery implements IRequest<List<Unit>> {
    private String name;
}
