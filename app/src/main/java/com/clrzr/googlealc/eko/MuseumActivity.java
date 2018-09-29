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

public class MuseumActivity extends AppCompatActivity implements ContentAdapter.RecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        initCollapsingToolbar();

        mContents = new ArrayList<>();
        mContents.add(new Content(getString(R.string.museums_kalakuta), getString(R.string.museums_address_kala), getString(R.string.museums_kala_year), R.drawable.museums_kala));
        mContents.add(new Content(getString(R.string.museums_nike), getString(R.string.museums_address_nike), getString(R.string.museums_nike_year), R.drawable.museums_nike));
        mContents.add(new Content(getString(R.string.museums_heritage), getString(R.string.museums_address_heritage), getString(R.string.museums_heritage_year), R.drawable.museums_heritage));
        mContents.add(new Content(getString(R.string.museums_national), getString(R.string.museums_address_national), getString(R.string.museums_national_year), R.drawable.museums_nation));

        RecyclerView recyclerView = findViewById(R.id.content_recycler_view);

        ContentAdapter mContentAdapter = new ContentAdapter(mContents, this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MuseumActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(MuseumActivity.this), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mContentAdapter);
        mContentAdapter.notifyDataSetChanged();
    }


    /**
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.appbarMuseums);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbarMuseums1);
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
                    collapsingToolbar.setTitle(getString(R.string.museum_subtitle));
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
        mToast = Toast.makeText(MuseumActivity.this, getString(R.string.selected_content_toast) + content.getAttractionName(), Toast.LENGTH_LONG);
        mToast.show();

    }
}
