package com.clean.application.auction.commands;

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
public class CreateAuctionCommandHandler implements IRequestHandler<CreateAuctionCommand, AuctionModel> {
   private AuctionRepository auctionRepository;
   private ItemDetailRepository itemDetailRepository;
   private StatusRepository statusRepository;
   private FiscalYearRepository fiscalYearRepository;
   private ItemRepository itemRepository;

    @Autowired
    CreateAuctionCommandHandler(
            AuctionRepository auctionRepository,
            ItemDetailRepository itemDetailRepository,
            StatusRepository statusRepository,
            FiscalYearRepository fiscalYearRepository,
            ItemRepository itemRepository
    )
    {
       this.auctionRepository = auctionRepository;
       this.itemDetailRepository = itemDetailRepository;
       this.statusRepository = statusRepository;
       this.fiscalYearRepository= fiscalYearRepository;
       this.itemRepository  = itemRepository;
    }
    @Override
    public AuctionModel handle(CreateAuctionCommand request) {
        Auction auction = auctionRepository.findById(request.getId()).orElse(null);
       if(auction == null){
           auction = new Auction();
        }
        auction.setItemSpecificationId(request.getItemSpecificationId());
        auction.setItemDetailId(request.getItemDetailId());
        auction.setQuantity(request.getQuantity());
        auction.setStatusId(request.getStatusId());
        auction.setDate(request.getDate());
        auction.setFiscalYearId(request.getFiscalYearId());
        auction.setPrice(request.getPrice());
        auction= auctionRepository.save(auction);
        AuctionModel model = AuctionModel.map(auction);
        String  itemDetail = itemDetailRepository.findById(auction.getItemDetailId()).get().getDetail();
        model.setItemDetailText(itemDetail);
        String status = statusRepository.findById(auction.getStatusId()).get().getName();
        model.setStatusText(status);
        int fiscalYear = fiscalYearRepository.findById(request.getFiscalYearId()).get().getShamsiYear();
        model.setFiscalYearText(fiscalYear);
        return model;
    }
}
