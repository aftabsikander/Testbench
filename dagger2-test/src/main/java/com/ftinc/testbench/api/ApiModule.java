package com.ftinc.testbench.api;

import android.content.Context;

import com.ftinc.testbench.BuildConfig;
import com.ftinc.testbench.util.LoganSquareConverter;
import com.ftinc.testbench.util.qualifiers.ApiUrl;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.api
 * Created by drew.heavner on 4/6/15.
 */
@Module(
    library = true,
    complete = false
)
public class ApiModule {

    @Provides @Singleton
    Endpoint provideApiEndpoint(@ApiUrl String url){
        return Endpoints.newFixedEndpoint(url);
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides @Singleton
    Converter provideRetrofitConverter(){
//        return new GsonConverter(new Gson());
        return new LoganSquareConverter();
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Context ctx,
                                   Endpoint endpoint,
                                   Client client,
                                   Converter converter,
                                   ApiHeaders headers) {

        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setRequestInterceptor(headers)
                .setErrorHandler(new RetrofitErrorHandler(ctx))
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.HEADERS_AND_ARGS : RestAdapter.LogLevel.BASIC)
                .setConverter(converter)
                .build();
    }

    @Provides @Singleton
    ApiService provideApiClient(RestAdapter adapter){
        return adapter.create(ApiService.class);
    }

}
