package com.ftinc.testbench.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.ftinc.testbench.R;
import com.ftinc.testbench.api.model.Character;

import butterknife.ButterKnife;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.ui.adapters
 * Created by drew.heavner on 4/7/15.
 */
public class CharacterAdapter extends BetterRecyclerAdapter<Character, CharacterAdapter.CharacterViewHolder>{

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder characterViewHolder, int i) {
        super.onBindViewHolder(characterViewHolder, i);



    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{



        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
