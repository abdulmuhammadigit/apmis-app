package com.clean.application.look.quries;

import com.clean.domain.entity.look.StockKeeperType;
import com.clean.persistence.look.StockKeeperTypeRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchStockKeeperTypeQueryHandler implements IRequestHandler<SearchStockKeeperTypeQuery, List<StockKeeperType>> {
    private StockKeeperTypeRepository repository;
    @Autowired
    SearchStockKeeperTypeQueryHandler(StockKeeperTypeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<StockKeeperType> handle(SearchStockKeeperTypeQuery request) {
        return repository.findAll();
    }
}
