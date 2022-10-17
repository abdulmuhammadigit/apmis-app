package com.clean.application.report.queries;

import com.clean.application.report.models.ItemReceiptQuantityModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class GetItemReceiptQuantityQuery implements IRequest<List<ItemReceiptQuantityModel>> {
    private Date startDate;
    private Date endDate;
    private Boolean localProduct;
}
