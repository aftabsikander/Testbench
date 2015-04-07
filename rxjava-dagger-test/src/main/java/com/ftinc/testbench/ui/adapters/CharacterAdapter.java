package com.ftinc.testbench.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.ftinc.testbench.R;
import com.ftinc.testbench.api.model.Character;
import com.ftinc.testbench.api.model.Image;
import com.ftinc.testbench.util.CircleTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.ui.adapters
 * Created by drew.heavner on 4/7/15.
 */
public class CharacterAdapter extends BetterRecyclerAdapter<Character, CharacterAdapter.CharacterViewHolder>{

    private Context mContext;
    private boolean mIsListMode = true;

    @Inject
    public CharacterAdapter(Context ctx){
        mContext = ctx;
    }

    public void setViewMode(boolean val){
        mIsListMode = val;
        notifyDataSetChanged();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_character_item, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_character_grid_item, parent, false);
        }
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int i) {
        super.onBindViewHolder(holder, i);

        Character character = getItem(i);

        // Bind the data
        holder.mLine1.setText(character.name);
        if(!TextUtils.isEmpty(character.description)) {
            holder.mLine2.setVisibility(View.VISIBLE);
            holder.mLine2.setText(character.description);
        }else{
            holder.mLine2.setVisibility(View.GONE);
        }

        character.getThumbnail()
                .map(new Func1<Image, String>() {
                    @Override
                    public String call(Image image) {
                        if (mIsListMode) {
                            return Image.Variant.STANDARD_XLARGE.createUrl(image);
                        }else{
                            return Image.Variant.PORTRAIT_UNCANNY.createUrl(image);
                        }
                    }
                })
                .subscribe(s -> {
                    // Using Picasso, load the image
                    RequestCreator creator = Picasso.with(mContext)
                            .load(s);
                    if (mIsListMode) creator.transform(new CircleTransform());
                    creator.into(holder.mAvatar);
                }, throwable -> Timber.e("Error loading thumbnail: %s", throwable.getLocalizedMessage()));
    }

    @Override
    public int getItemViewType(int position) {
        return mIsListMode? 0 : 1;
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.avatar)    ImageView mAvatar;
        @InjectView(R.id.line_1)    TextView mLine1;
        @InjectView(R.id.line_2)    TextView mLine2;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
