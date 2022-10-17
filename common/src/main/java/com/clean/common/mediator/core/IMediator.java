package com.clean.common.mediator.core;

import java.util.concurrent.CompletableFuture;

public interface IMediator {

    <TRequest extends IRequest<R>,R> R send(TRequest request);

    <TRequest extends IRequest<R>,R> CompletableFuture<R> sendAsync(TRequest request);
}
