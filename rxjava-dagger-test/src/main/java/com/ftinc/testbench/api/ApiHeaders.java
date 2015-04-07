/*
 * Copyright (c) 52apps 2014. All rights reserved.
 */

package com.ftinc.testbench.api;

import com.ftinc.kit.util.FormatUtils;
import com.ftinc.kit.util.Utils;
import com.ftinc.testbench.util.qualifiers.PrivateKey;
import com.ftinc.testbench.util.qualifiers.PublicKey;

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

    @Inject @PublicKey
    String mPublicKey;

    @Inject @PrivateKey
    String mPrivateKey;

    @Inject
    public ApiHeaders(){}

    @Override
    public void intercept(RequestFacade request) {
        long timestamp = Utils.time();
        String preHash = String.format("%d%s%s", timestamp, mPrivateKey, mPublicKey);
        String hash = FormatUtils.generateMD5String(preHash);

        request.addEncodedQueryParam("ts", String.valueOf(timestamp));
        request.addEncodedQueryParam("apikey", mPublicKey);
        request.addEncodedQueryParam("hash", hash);
    }
}
