package com.ftinc.testbench;

import android.content.Context;

import com.ftinc.testbench.api.ApiModule;
import com.ftinc.testbench.data.DataModule;
import com.ftinc.testbench.ui.UiModule;
import com.ftinc.testbench.util.qualifiers.ApiUrl;
import com.ftinc.testbench.util.qualifiers.PrivateKey;
import com.ftinc.testbench.util.qualifiers.PublicKey;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench
 * Created by drew.heavner on 4/6/15.
 */
@Module(
    injects = App.class,
    includes = {
        ApiModule.class,
        DataModule.class,
        UiModule.class
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
        return "https://gateway.marvel.com/v1/public/";
    }

    @Provides @Singleton @PublicKey
    String providePublicKey(){
        return "ab79550f58edd71887e994fb3157dc89";
    }

    @Provides @Singleton @PrivateKey
    String providePrivateKey(){
        return "9ce4d3751e8081b826f20ac5292cd3dc039fa5df";
    }

}
