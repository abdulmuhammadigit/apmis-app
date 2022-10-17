package com.clean.common.base;

import com.clean.common.GeneralResponse;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequest;

public abstract class BaseController {
    protected IMediator mediator;

    public GeneralResponse dispatchRequest(IRequest request){
        GeneralResponse response = new GeneralResponse();
        try{
            response.data = mediator.send(request);
            response.successful = true;
        }
        catch (Exception ex){
            response.successful =false; 
            response.error = ex.getMessage();
        }
        return response;
    }
}
