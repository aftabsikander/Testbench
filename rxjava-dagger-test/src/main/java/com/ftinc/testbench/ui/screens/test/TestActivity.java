package com.ftinc.testbench.ui.screens.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ftinc.kit.mvp.BaseActivity;
import com.ftinc.testbench.R;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class TestActivity extends BaseActivity implements TestView{

    /***********************************************************************************************
     *
     * Variables
     *
     */

    @InjectView(R.id.recycler)
    RecyclerView mRecycler;

    @Inject
    TestPresenter mPresenter;

    /***********************************************************************************************
     *
     * Lifecycle Methods
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);

        // TODO: Setup Ui
        mPresenter.downloadCharacters(50, 0);

    }

    @Override
    protected Object[] getModules() {
        return new Object[]{
            new TestModule(this)
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
        Snackbar.with(this)
                .text(s)
                .type(SnackbarType.MULTI_LINE)
                .swipeToDismiss(true)
                .show(this);
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
