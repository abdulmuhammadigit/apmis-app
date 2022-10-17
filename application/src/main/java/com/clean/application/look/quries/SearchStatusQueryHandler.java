package com.clean.application.look.quries;

import com.clean.domain.entity.look.Status;
import com.clean.persistence.look.StatusRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchStatusQueryHandler implements IRequestHandler<SearchStatusQuery, List<Status>> {
    private StatusRepository repository;
    @Autowired
    SearchStatusQueryHandler(StatusRepository repository)
    {
        this.repository = repository;
    }
    @Override
    public List<Status> handle(SearchStatusQuery request) {
        Specification specification = null;
        if(request.getDbObject() != null && !request.getDbObject().isEmpty()){
            specification = Specification.where((Specification<Status>)(root,query,critiriaBuilder)->critiriaBuilder.equal(root.get("dbObjet"),request.getDbObject()));
        }
        if(request.getCategory() != null && !request.getCategory().isEmpty()){
            specification = Specification.where((Specification<Status>)(root,query,critiriaBuilder)->critiriaBuilder.equal(root.get("category"),request.getCategory()));
        }
        return repository.findAll(specification);
    }
}
