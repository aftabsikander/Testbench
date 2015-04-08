package com.ftinc.testbench.ui.screens.characters.viewer;

import com.ftinc.testbench.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by r0adkll on 4/7/15.
 */
@Module(
    injects = CharacterViewerActivity.class,
    addsTo = UiModule.class,
    complete = false
)
public class CharacterViewerModule {

    private CharacterViewerView mView;

    public CharacterViewerModule(CharacterViewerView mView) {
        this.mView = mView;
    }

    @Provides @Singleton
    CharacterViewerView provideView(){
        return mView;
    }

    @Provides @Singleton
    CharacterViewerPresenter providePresenter(CharacterViewerView view){
        return new CharacterViewerPresenterImpl(mView);
    }

}
