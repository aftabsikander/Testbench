package com.ftinc.testbench.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 *
 */
@Module(
    library = true,
    complete = false
)
public class DataModule {

    @Provides @Singleton
    SharedPreferences provideDefaultPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }



}
