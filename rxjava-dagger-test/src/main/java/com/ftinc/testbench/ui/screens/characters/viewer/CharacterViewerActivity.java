package com.ftinc.testbench.ui.screens.characters.viewer;

import android.app.Activity;
import android.os.Bundle;

import com.ftinc.testbench.R;
import com.ftinc.testbench.ui.model.BaseTestActivity;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import javax.inject.Inject;

/**
 * Created by r0adkll on 4/7/15.
 */
public class CharacterViewerActivity extends BaseTestActivity implements CharacterViewerView{

    /***********************************************************************************************
     *
     * Variables
     *
     */

    @Inject
    CharacterViewerPresenter mPresenter;

    /***********************************************************************************************
     *
     * Lifecycle Methods
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_viewer);


    }

    @Override
    protected Object[] getModules() {
        return new Object[]{
            new CharacterViewerModule(this)
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
                .swipeToDismiss(false)
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
