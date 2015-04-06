/*
 * Copyright (c) 52apps 2014. All rights reserved.
 */

package com.ftinc.testbench.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RequestInterceptor;

/**
 * Project: TradeVersity
 * Package: com.ftinc.tradeversity.api
 * Created by drew.heavner on 4/3/15.
 */
@Singleton
public final class ApiHeaders implements RequestInterceptor {

    @Inject
    public ApiHeaders(){}

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");

    }
}
