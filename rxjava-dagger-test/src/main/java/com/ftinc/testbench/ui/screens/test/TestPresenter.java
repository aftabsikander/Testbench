package com.ftinc.testbench.ui.screens.test;

import com.ftinc.kit.mvp.IBaseActivityPresenter;

/**
 * Created by r0adkll on 4/7/15.
 */
public interface TestPresenter  {

    void downloadCharacters(int limit, int offset);

    void loadCharacters();

}
