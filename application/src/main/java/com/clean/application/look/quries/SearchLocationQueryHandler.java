package com.clean.application.look.quries;

import com.clean.domain.entity.look.Location;
import com.clean.persistence.look.LocationRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchLocationQueryHandler implements IRequestHandler<SearchLocationQuery, List<Location>> {
    private LocationRepository repository;
    @Autowired
    SearchLocationQueryHandler(LocationRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Location> handle(SearchLocationQuery request) {
        return repository.findAll();
    }
}
