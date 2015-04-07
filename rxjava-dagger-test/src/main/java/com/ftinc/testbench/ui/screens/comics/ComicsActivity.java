package com.ftinc.testbench.ui.screens.comics;

import android.app.Activity;
import android.os.Bundle;

import com.ftinc.testbench.R;
import com.ftinc.testbench.ui.model.BaseTestActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.ui.screens.comics
 * Created by drew.heavner on 4/7/15.
 */
public class ComicsActivity extends BaseTestActivity implements ComicsView{

    /***********************************************************************************************
     *
     * Variables
     *
     */

    @Inject
    ComicsPresenter mPresenter;

    /***********************************************************************************************
     *
     * Lifecycle Methods
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);
        ButterKnife.inject(this);

    }

    @Override
    protected Object[] getModules() {
        return new Object[]{
            new ComicsModule(this)
        };
    }

    /***********************************************************************************************
     *
     * View Methods
     *
     */

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showSnackBar(String s) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void closeKeyboard() {

    }
}
