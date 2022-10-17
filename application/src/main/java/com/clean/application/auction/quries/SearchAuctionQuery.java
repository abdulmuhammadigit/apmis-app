package com.clean.application.auction.quries;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchAuctionQuery implements IRequest<List<AuctionModel>> {
    private Integer itemDetailId;
    private short statusId;
    private int fiscalYearId;
}
