package com.clean.application.auction.commands;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionPricingBoardCommand implements IRequest<AuctionModel> {
    private long auctionId;
    private float pricingBoard;
}
