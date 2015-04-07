package com.ftinc.testbench.ui.screens.test;

import android.os.Bundle;

import com.ftinc.testbench.api.ApiService;
import com.ftinc.testbench.api.model.*;
import com.ftinc.testbench.api.model.Character;
import com.ftinc.testbench.api.model.responses.CharacterResponse;

import java.util.List;

import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
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

    @Override
    public Observable<List<Character>> downloadCharacters(int limit, int offset) {

        return mService.getCharacters(null, null, limit, offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<CharacterResponse, Observable<List<Character>>>() {
                    @Override
                    public Observable<List<Character>> call(CharacterResponse characterResponse) {
                        return Observable.just(characterResponse.data.results);
                    }
                });

    }

}
