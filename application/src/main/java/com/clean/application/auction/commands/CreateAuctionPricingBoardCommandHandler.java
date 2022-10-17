package com.clean.application.auction.commands;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Auction;
import com.clean.persistence.auction.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAuctionPricingBoardCommandHandler implements IRequestHandler<CreateAuctionPricingBoardCommand, AuctionModel> {
    private AuctionRepository auctionRepository;
    @Autowired
    CreateAuctionPricingBoardCommandHandler(AuctionRepository auctionRepository){
        this.auctionRepository = auctionRepository;
    }
    @Override
    public AuctionModel handle(CreateAuctionPricingBoardCommand request) {
        Auction auction = auctionRepository.findById(request.getAuctionId()).orElseThrow(()->new RuntimeException("جنس لیلام شده دریافت نگردید!"));
        auction.setPricingBoard(request.getPricingBoard());
        float finalPrice = request.getPricingBoard();
        auction.setFinalPrice(finalPrice);
        if(request.getPricingBoard() > auction.getPrice()){
            throw new RuntimeException("قیمت نظر کمیسیون نمیتواند بزرگتر از قیمت قبلی جنس باشد!");
        }
        auction= auctionRepository.save(auction);
        return AuctionModel.map(auction);

    }
}
