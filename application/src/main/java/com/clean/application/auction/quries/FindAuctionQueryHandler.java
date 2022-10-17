package com.clean.application.auction.quries;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Auction;
import com.clean.persistence.configuration.FiscalYearRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.auction.AuctionRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.look.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAuctionQueryHandler implements IRequestHandler<FindAuctionQuery, AuctionModel> {
   private AuctionRepository auctionRepository;
    private ItemDetailRepository itemDetailRepository;
    private StatusRepository statusRepository;
    private FiscalYearRepository fiscalYearRepository;

    @Autowired
   public FindAuctionQueryHandler(
           AuctionRepository auctionRepository,
           ItemDetailRepository itemDetailRepository,
           StatusRepository statusRepository,
           FiscalYearRepository fiscalYearRepository
   ){
       this.auctionRepository= auctionRepository;
       this.itemDetailRepository = itemDetailRepository;
       this.statusRepository = statusRepository;
       this.fiscalYearRepository = fiscalYearRepository;
   }
    @Override
    public AuctionModel handle(FindAuctionQuery request) {
        Auction auction = auctionRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("جنس برای لیلام دریافت نگردید."));
        AuctionModel model = AuctionModel.map(auction);
        String  itemDetail = itemDetailRepository.findById(auction.getItemDetailId()).get().getDetail();
        model.setItemDetailText(itemDetail);
        String status = statusRepository.findById(auction.getStatusId()).get().getName();
        model.setStatusText(status);
        int fiscalYear = fiscalYearRepository.findById(auction.getFiscalYearId()).get().getShamsiYear();
        model.setFiscalYearText(fiscalYear);
        return model;
    }
}
