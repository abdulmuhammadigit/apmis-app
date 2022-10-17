package com.clean.common.mediator.core;

public interface IRegistry {
    <T extends IRequest<R>,R > IRequestHandler<T,R> getRequestHandler(Class<T> requestType);
}
