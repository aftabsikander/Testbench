package com.ftinc.testbench;

import android.app.Application;

import com.ftinc.kit.mvp.BaseApplication;
import com.ftinc.kit.mvp.modules.Mods;

import ollie.Ollie;
import timber.log.Timber;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench
 * Created by drew.heavner on 4/6/15.
 */
public class App extends BaseApplication {

    private static final String DB_NAME = "testbench.db";
    private static final int DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Ollie
        Ollie.with(this)
            .setName(DB_NAME)
            .setVersion(DB_VERSION)
            .setLogLevel(Ollie.LogLevel.FULL)
            .init();

    }

    @Override
    public Timber.Tree[] getDebugTrees() {
        return new Timber.Tree[]{
            new Timber.DebugTree()
        };
    }

    @Override
    public Timber.Tree[] getReleaseTrees() {
        return new Timber.Tree[]{
            new Timber.DebugTree()
        };
    }

    @Override
    public Mods getMods() {
        return application -> new Object[]{
            new AppModule(application)
        };
    }
}
