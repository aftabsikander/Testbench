package com.ftinc.testbench;

import android.content.Context;

import com.ftinc.testbench.api.ApiModule;
import com.ftinc.testbench.data.DataModule;
import com.ftinc.testbench.util.qualifiers.ApiUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench
 * Created by drew.heavner on 4/6/15.
 */
@Module(
    includes = {
        ApiModule.class,
        DataModule.class
    },
    library = true
)
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides @Singleton @ApiUrl
    String provideApiUrl(){
        return "http://gateway.marvel.com/v1/public/";
    }

}
