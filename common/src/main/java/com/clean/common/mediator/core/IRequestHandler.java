package com.clean.common.mediator.core;

public interface IRequestHandler <Request extends IRequest<RType>,RType > {
    RType handle(Request request);
}
