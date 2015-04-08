package com.ftinc.testbench.ui;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by r0adkll on 4/7/15.
 */
@Module(
    library = true,
    complete = false
)
public class UiModule {

    @Provides @Singleton
    InputMethodManager provideIMM(Context ctx){
        return (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
