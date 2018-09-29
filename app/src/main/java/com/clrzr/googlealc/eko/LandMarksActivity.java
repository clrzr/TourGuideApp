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

public class LandMarksActivity extends AppCompatActivity implements ContentAdapter.RecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmarks);
        initCollapsingToolbar();

        //Create an ArrayList of features using the constructor declared in the Feature class
        mContents = new ArrayList<>();
        mContents.add(new Content(getString(R.string.landmark_tinubu_square), getString(R.string.landmark_address_tinubu_square), getString(R.string.landmark_tinubu_year), R.drawable.landmarks_tinubu));
        mContents.add(new Content(getString(R.string.landmark_tbs), getString(R.string.landmark_address_tbs), getString(R.string.landmark_tbs_year), R.drawable.landmarks_tbs));
        mContents.add(new Content(getString(R.string.landmark_lcc), getString(R.string.landmark_address_lcc), getString(R.string.landmark_lcc_year), R.drawable.landmarks_lcc));
        mContents.add(new Content(getString(R.string.landmark_ccoc), getString(R.string.landmark_address_ccoc), getString(R.string.landmark_ccoc_year), R.drawable.landmarks_ccoc));

        //Find the recyclerView
        RecyclerView recyclerView = findViewById(R.id.content_recycler_view);

        //An instance of the adapter
        ContentAdapter mContentsAdapter = new ContentAdapter(mContents, this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(LandMarksActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(LandMarksActivity.this), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //Set adapter
        recyclerView.setAdapter(mContentsAdapter);

        // notify adapter about data set changes so that it will render the list with new data
        mContentsAdapter.notifyDataSetChanged();
    }


    /**
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.appbarLandmarks);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbarLandmarks1);
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
                    collapsingToolbar.setTitle(getString(R.string.landmarks_subtitle));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    private ArrayList<Content> mContents;

    /*Toast*/
    private Toast mToast;


    @Override
    public void onRecyclerViewItemClicked(View view, int position) {
        //Get the current content
        Content content = mContents.get(position);

        //Cancel a toast is the user clicks rapidly
        if (mToast != null) {
            mToast.cancel();
        }

        //Make a toast
        mToast = Toast.makeText(LandMarksActivity.this, getString(R.string.selected_content_toast) + content.getAttractionName(), Toast.LENGTH_LONG);
        mToast.show();

    }
}
