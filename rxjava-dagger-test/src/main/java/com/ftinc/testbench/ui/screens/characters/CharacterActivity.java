package com.ftinc.testbench.ui.screens.characters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.ftinc.kit.util.Utils;
import com.ftinc.kit.widget.DividerItemDecoration;
import com.ftinc.kit.widget.EmptyView;
import com.ftinc.testbench.R;
import com.ftinc.testbench.api.model.Character;
import com.ftinc.testbench.ui.adapters.CharacterAdapter;
import com.ftinc.testbench.ui.model.BaseTestActivity;
import com.ftinc.testbench.ui.model.DividerSpacerItemDecoration;
import com.ftinc.testbench.util.EndlessRecyclerOnScrollListener;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icicle;
import rx.android.app.AppObservable;
import timber.log.Timber;


public class CharacterActivity extends BaseTestActivity implements CharacterView, BetterRecyclerAdapter.OnItemClickListener<Character>, SearchView.OnQueryTextListener {

    /***********************************************************************************************
     *
     * Constants
     *
     */

    public static final int PAGE_COUNT = 50;

    /***********************************************************************************************
     *
     * Variables
     *
     */

    @InjectView(R.id.loading)
    ProgressBar mLoading;

    @InjectView(R.id.empty_view)
    EmptyView mEmptyView;

    @InjectView(R.id.recycler)
    RecyclerView mRecycler;

    @Inject
    CharacterPresenter mPresenter;

    @Inject
    CharacterAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mDividerDecor;

    @Icicle
    boolean mIsListMode = true;

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

        mAdapter.setEmptyView(mEmptyView);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(this);

        if(mIsListMode){
            setupListViewMode();
        }else {
            setupGridViewMode();
        }
        mAdapter.setViewMode(mIsListMode);

        addSubscription(AppObservable.bindActivity(this, mPresenter.downloadCharacters(PAGE_COUNT, 0))
                .subscribe(characters -> {
                    mAdapter.clear();
                    mAdapter.addAll(characters);
                    mAdapter.notifyDataSetChanged();
                }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_characters, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
//        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem actionView = menu.findItem(R.id.action_view);
        actionView.setIcon(mIsListMode ? R.drawable.ic_grid_white_24dp:R.drawable.ic_list_white_24dp);
        actionView.setTitle(mIsListMode ? R.string.action_view_list : R.string.action_view_grid);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_view:

                if(mIsListMode){
                    // TODO: Switch to grid mode
                    setupGridViewMode();
                    mAdapter.setViewMode(false);
                    mIsListMode = false;
                }else{
                    // TODO: Switch to list mode
                    setupListViewMode();
                    mAdapter.setViewMode(true);
                    mIsListMode = true;
                }
                supportInvalidateOptionsMenu();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Object[] getModules() {
        return new Object[]{
            new CharacterModule(this)
        };
    }

    @Override
    public void onItemClick(View view, Character character, int i) {
        Timber.d("Character selected: %s", character.name);
    }

    /***********************************************************************************************
     *
     * Helper Methods
     *
     */

    private void setupListViewMode(){

        // Set Layout manager to linear layout mode
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        if(mDividerDecor != null) mRecycler.removeItemDecoration(mDividerDecor);
        mDividerDecor = new DividerItemDecoration(getResources().getDrawable(R.drawable.inset_divider), false, false);
        mRecycler.addItemDecoration(mDividerDecor);
        mRecycler.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                int offset = current_page * PAGE_COUNT;
                addSubscription(AppObservable.bindActivity(CharacterActivity.this, mPresenter.downloadCharacters(PAGE_COUNT, offset))
                        .subscribe(characters -> {
                            mAdapter.addAll(characters);
                            mAdapter.notifyDataSetChanged();
                        }));
            }
        });

    }

    private void setupGridViewMode(){

        mLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mLayoutManager);
        if(mDividerDecor != null) mRecycler.removeItemDecoration(mDividerDecor);
        mDividerDecor = new DividerSpacerItemDecoration(Utils.dpToPx(this, 1), true);
        mRecycler.addItemDecoration(mDividerDecor);
        mRecycler.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                int offset = current_page * PAGE_COUNT;
                addSubscription(AppObservable.bindActivity(CharacterActivity.this, mPresenter.downloadCharacters(PAGE_COUNT, offset))
                        .subscribe(characters -> {
                            mAdapter.addAll(characters);
                            mAdapter.notifyDataSetChanged();
                        }));
            }
        });

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
        mLoading.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.GONE);
        if(mAdapter.getItemCount() == 0) mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeKeyboard() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
