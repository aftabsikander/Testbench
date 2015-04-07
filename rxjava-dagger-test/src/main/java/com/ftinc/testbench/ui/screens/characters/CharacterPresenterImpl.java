package com.ftinc.testbench.ui.screens.characters;

import com.ftinc.testbench.api.ApiService;
import com.ftinc.testbench.api.model.Character;
import com.ftinc.testbench.api.model.responses.CharacterResponse;

import java.util.List;

import ollie.Ollie;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by r0adkll on 4/7/15.
 */
public class CharacterPresenterImpl implements CharacterPresenter {

    private CharacterView mView;
    private ApiService mService;

    public CharacterPresenterImpl(CharacterView view, ApiService service) {
        this.mView = view;
        this.mService = service;
    }

    @Override
    public Observable<List<Character>> downloadCharacters(int limit, int offset) {
        mView.showLoading();

        return mService.getCharacters(null, null, limit, offset)
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Func1<CharacterResponse, Observable<List<Character>>>() {
                    @Override
                    public Observable<List<Character>> call(CharacterResponse characterResponse) {
                        Timber.i("Reconstructing retrofit observable into character list observable");
                        return Observable.just(characterResponse.data.results);
                    }
                })
                .doOnNext(characters -> {
                    long start = System.currentTimeMillis();
                    Ollie.getDatabase().beginTransaction();
                    try {
                        for (Character character : characters) {
                            character.saveCharacter();
                        }
                        Ollie.getDatabase().setTransactionSuccessful();
                    } finally {
                        Ollie.getDatabase().endTransaction();
                    }

                    Timber.i("Characters saved in %d ms", (System.currentTimeMillis() - start));
                })
                .doOnCompleted(() -> mView.getActivity().runOnUiThread(() -> mView.hideLoading()))
                .doOnError(throwable -> {
                    mView.getActivity().runOnUiThread(() -> {
                        mView.showSnackBar(throwable.getLocalizedMessage());
                        mView.hideLoading();
                    });
                });

    }

}
