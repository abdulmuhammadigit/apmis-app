package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequesSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;

@Data
public class SearchItemRequestQuery implements IRequest<List<ItemRequesSearchModel>> {
    private String code;
    private String documentNumber;
    private boolean distributionSearch;
}
