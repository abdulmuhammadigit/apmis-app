package com.clean.application.auction.models;

import com.clean.domain.entity.stc.Auction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuctionModel {
    private long id;
    private int itemSpecificationId;
    private int itemDetailId;
    private int quantity;
    private Integer statusId;
    private Date date;
    private int fiscalYearId;
    private float price;
    private float pricingBoard;
    private float finalPrice;
    private String itemDetailText;
    private String statusText;
    private int fiscalYearText;
    private String itemText;
    public  static AuctionModel map(Auction auction){
        AuctionModel model = new AuctionModel();
        model.setId(auction.getId());
        model.setItemDetailId(auction.getItemDetailId());
        model.setQuantity(auction.getQuantity());
        model.setStatusId(auction.getStatusId());
        model.setDate(auction.getDate());
        model.setFiscalYearId(auction.getFiscalYearId());
        model.setPrice(auction.getPrice());
        model.setPricingBoard(auction.getPricingBoard());
        model.setFinalPrice(auction.getFinalPrice());
        model.setItemSpecificationId(auction.getItemSpecificationId());
        return  model;
    }
}
