package com.clrzr.googlealc.eko;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class HotelsActivity extends AppCompatActivity implements ContentAdapter.RecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        initCollapsingToolbar();


        mContents = new ArrayList<>();
        mContents.add(new Content(getString(R.string.hotel_federal_palace), getString(R.string.hotels_address_federal), getString(R.string.hotels_federal_year), R.drawable.hotels_fede));
        mContents.add(new Content(getString(R.string.hotels_radblu), getString(R.string.hotels_address_radblu), getString(R.string.hotels_radblu_year), R.drawable.hotels_radblu));
        mContents.add(new Content(getString(R.string.hotels_quilox), getString(R.string.hotels_address_quilox), getString(R.string.hotels_quilox_year), R.drawable.hotels_quilox));
        mContents.add(new Content(getString(R.string.hotels_get), getString(R.string.hotels_address_get), getString(R.string.hotels_get_year), R.drawable.hotels_get));

        RecyclerView recyclerView = findViewById(R.id.content_recycler_view);

        ContentAdapter mContentsAdapter = new ContentAdapter(mContents, this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HotelsActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(HotelsActivity.this), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mContentsAdapter);
        mContentsAdapter.notifyDataSetChanged();
    }


    /**
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.appbarHotels);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbarHotels1);
        appBarLayout.setExpanded(true);

        // hiding & showing the label when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.hotels_subtitle));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    private ArrayList<Content> mContents;

    private Toast mToast;


    @Override
    public void onRecyclerViewItemClicked(View view, int position) {

        Content content = mContents.get(position);

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(HotelsActivity.this, getString(R.string.selected_content_toast) + content.getAttractionName(), Toast.LENGTH_LONG);
        mToast.show();

    }
}
