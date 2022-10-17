package com.clean.application.auction.quries;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Auction;
import com.clean.persistence.auction.AuctionRepository;
import com.clean.persistence.configuration.FiscalYearRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.look.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindBoardDecisionQueryHandler implements IRequestHandler<FindBoardDecisionQuery , AuctionModel> {
   private AuctionRepository auctionRepository;
   private ItemDetailRepository itemDetailRepository;
   private StatusRepository statusRepository;
   private  FiscalYearRepository fiscalYearRepository;
   @Autowired
   FindBoardDecisionQueryHandler(
           AuctionRepository auctionRepository,
           ItemDetailRepository itemDetailRepository,
           StatusRepository statusRepository,
           FiscalYearRepository fiscalYearRepository
   ){
       this.auctionRepository = auctionRepository;
       this.itemDetailRepository = itemDetailRepository;
       this.statusRepository = statusRepository;
       this.fiscalYearRepository = fiscalYearRepository;
   }
    @Override
    public AuctionModel handle(FindBoardDecisionQuery request) {
        Auction auction = auctionRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("لیلام دریافت نگردید !"));
        AuctionModel model= AuctionModel.map(auction);
        model.setItemDetailText(itemDetailRepository.findById(auction.getItemDetailId()).get().getDetail());
        model.setStatusText(statusRepository.findById(auction.getStatusId()).get().getName());
        model.setFiscalYearText(fiscalYearRepository.findById(auction.getFiscalYearId()).get().getShamsiYear());
        return model;
    }
}
