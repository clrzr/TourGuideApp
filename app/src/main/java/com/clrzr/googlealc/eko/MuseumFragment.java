package com.clrzr.googlealc.eko;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MuseumFragment extends Fragment implements ContentAdapter.RecyclerViewClickListener {

    public MuseumFragment(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_item_recycler_view, container, false);

        final View MainView = Objects.requireNonNull(getActivity()).findViewById(R.id.welcome_data);
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_fragment);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.museum_subtitle));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
                MainView.setVisibility(View.VISIBLE);
            }
        });

        mContents = new ArrayList<>();
        mContents.add(new Content(getString(R.string.museums_kalakuta), getString(R.string.museums_address_kala), getString(R.string.museums_kala_year), R.drawable.museums_kala));
        mContents.add(new Content(getString(R.string.museums_nike), getString(R.string.museums_address_nike), getString(R.string.museums_nike_year), R.drawable.museums_nike));
        mContents.add(new Content(getString(R.string.museums_heritage), getString(R.string.museums_address_heritage), getString(R.string.museums_heritage_year), R.drawable.museums_heritage));
        mContents.add(new Content(getString(R.string.museums_national), getString(R.string.museums_address_national), getString(R.string.museums_national_year), R.drawable.museums_national));

        RecyclerView recyclerView = rootView.findViewById(R.id.content_recycler_view);

        ContentAdapter mContentsAdapter = new ContentAdapter(mContents, this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mContentsAdapter);
        mContentsAdapter.notifyDataSetChanged();

        return rootView;
    }

    /**
     * Will show and hide the toolbar title on scroll
     */
    @Override
    public void onStart() {
        super.onStart();

        final View MainScreen = Objects.requireNonNull(getActivity()).findViewById(R.id.welcome_data);

        Objects.requireNonNull(getView()).setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Objects.requireNonNull(getActivity()).onBackPressed();
                    MainScreen.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
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
        mToast = Toast.makeText(getActivity(), getString(R.string.selected_content_toast) + content.getAttractionName(), Toast.LENGTH_LONG);
        mToast.show();

    }
}
