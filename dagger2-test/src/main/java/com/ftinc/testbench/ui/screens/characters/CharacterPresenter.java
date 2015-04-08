package com.ftinc.testbench.ui.screens.characters;

import com.ftinc.kit.mvp.IBaseActivityPresenter;
import com.ftinc.testbench.api.model.*;
import com.ftinc.testbench.api.model.Character;

import java.util.List;

import rx.Observable;

/**
 * Created by r0adkll on 4/7/15.
 */
public interface CharacterPresenter {

    Observable<List<Character>> downloadCharacters(int limit, int offset);

}
