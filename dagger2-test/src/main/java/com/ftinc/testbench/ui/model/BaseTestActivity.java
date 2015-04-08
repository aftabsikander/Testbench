package com.ftinc.testbench.ui.model;

import android.os.Bundle;

import com.ftinc.kit.mvp.BaseActivity;

import icepick.Icepick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.ui.model
 * Created by drew.heavner on 4/7/15.
 */
public abstract class BaseTestActivity extends BaseActivity {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    protected void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }

    protected void removeSubscription(Subscription subscription){
        mCompositeSubscription.remove(subscription);
    }

}
