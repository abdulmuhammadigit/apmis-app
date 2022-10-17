package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.PageModel;
import com.clean.application.security.queries.GetPageListQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.configuration.PagesRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetPageListQueryHandler implements IRequestHandler<GetPageListQuery, List<PageModel>> {
    private PagesRepository pagesRepository;

    public GetPageListQueryHandler(PagesRepository pagesRepository){
        this.pagesRepository = pagesRepository;
    }

    @Override
    public List<PageModel> handle(GetPageListQuery request) {
        return pagesRepository.findAll().stream().map(PageModel::Map).collect(Collectors.toList());
    }
}
