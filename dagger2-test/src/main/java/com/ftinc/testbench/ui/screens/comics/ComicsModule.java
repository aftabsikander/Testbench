package com.ftinc.testbench.ui.screens.comics;

import com.ftinc.testbench.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.ui.screens.comics
 * Created by drew.heavner on 4/7/15.
 */
@Module(
    injects = ComicsActivity.class,
    addsTo = UiModule.class,
    complete = false
)
public class ComicsModule {

    private ComicsView mView;

    public ComicsModule(ComicsView mView) {
        this.mView = mView;
    }

    @Provides @Singleton
    ComicsView provideView(){
        return mView;
    }

    @Provides @Singleton
    ComicsPresenter providePresenter(ComicsView view){
        return new ComicsPresenterImpl(view);
    }
}
