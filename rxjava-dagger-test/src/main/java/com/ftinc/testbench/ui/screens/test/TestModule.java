package com.ftinc.testbench.ui.screens.test;

import com.ftinc.testbench.api.ApiService;
import com.ftinc.testbench.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by r0adkll on 4/7/15.
 */
@Module(
    injects = TestActivity.class,
    addsTo = UiModule.class,
    complete = false
)
public class TestModule {

    private TestView mView;

    public TestModule(TestView view) {
        this.mView = view;
    }

    @Provides @Singleton
    TestView provideView(){
        return mView;
    }

    @Provides @Singleton
    TestPresenter providePresenter(TestView view,
                                   ApiService service){
        return new TestPresenterImpl(view, service);
    }
}
