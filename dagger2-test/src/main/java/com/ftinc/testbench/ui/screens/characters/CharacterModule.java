package com.ftinc.testbench.ui.screens.characters;

import com.ftinc.testbench.api.ApiService;
import com.ftinc.testbench.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by r0adkll on 4/7/15.
 */
@Module(
    injects = CharacterActivity.class,
    addsTo = UiModule.class,
    complete = false
)
public class CharacterModule {

    private CharacterView mView;

    public CharacterModule(CharacterView view) {
        this.mView = view;
    }

    @Provides @Singleton
    CharacterView provideView(){
        return mView;
    }

    @Provides @Singleton
    CharacterPresenter providePresenter(CharacterView view,
                                   ApiService service){
        return new CharacterPresenterImpl(view, service);
    }
}
