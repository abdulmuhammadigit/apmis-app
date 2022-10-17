package com.clean.application.query;

import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GetPagesQueryHandler implements IRequestHandler<GetPagesQuery,String> {
    @Override
    public String handle(GetPagesQuery request) {

        return "home";
    }
}
