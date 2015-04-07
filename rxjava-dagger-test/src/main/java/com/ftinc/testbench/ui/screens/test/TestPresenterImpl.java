package com.ftinc.testbench.ui.screens.test;

import android.os.Bundle;

import com.ftinc.testbench.api.ApiService;
import com.ftinc.testbench.api.model.*;
import com.ftinc.testbench.api.model.Character;

import java.util.List;

import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by r0adkll on 4/7/15.
 */
public class TestPresenterImpl implements TestPresenter {

    private TestView mView;
    private ApiService mService;

    public TestPresenterImpl(TestView view, ApiService service) {
        this.mView = view;
        this.mService = service;
    }

    @DebugLog
    @Override
    public void downloadCharacters(int limit, int offset) {

        // Use the API Client to download a list of characters from the Marvel API
        mService.getCharacters(null, null, limit, offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    Timber.e(throwable, throwable.getLocalizedMessage());
                    return null;
                })
                .doOnError(throwable -> Timber.e(throwable, throwable.getLocalizedMessage()))
                .subscribe(marvelResponse -> {

                    Timber.i("Marvel Characters Observed:\n" +
                            "\tResponse: %s", marvelResponse.toString());

                    // Save the data response
                    List<Character> results = marvelResponse.data.results;
                    for(Character result: results){
                        result.saveCharacter();
                    }

                });

    }

    @Override
    public void loadCharacters() {

    }
}
