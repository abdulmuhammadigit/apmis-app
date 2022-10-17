package com.clean.application.auction.commands;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionCommand implements IRequest<AuctionModel> {
    private long id;
    private int itemSpecificationId;
    private int itemDetailId;
    private int quantity;
    private Integer statusId;
    private Date date;
    private int fiscalYearId;
    private float price;
}
