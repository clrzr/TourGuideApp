package com.clrzr.googlealc.eko;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

    /*The ArrayList that contains the list of features*/
    private ArrayList<Content> mContents;

    /*Context of the application*/
    private Context mContext;

    /*Variable for the click listener*/
    final private RecyclerViewClickListener mRecyclerViewClickListener;

    /**
     * This is the constructor to be used
     *
     * @param attractions                  is the ArrayList of {@link Content} objects
     *
     */
    public ContentAdapter(ArrayList<Content> attractions, RecyclerViewClickListener recyclerViewClickListener) {
        this.mContents = attractions;
        this.mRecyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        //Inflate the item and return a new ViewHolder
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.content_item_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Get the current feature
        Content currentContent = mContents.get(position);

        //Set the appropriate attributes of the feature to the corresponding views
        holder.attractionName.setText(currentContent.getAttractionName());
        holder.attractionAddress.setText(currentContent.getAttractionAddress());
        holder.attractionYear.setText(currentContent.getAttractionYear());
        holder.featureImage.setImageResource(currentContent.getImageResourceId());

    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    /**
     * The ViewHolder class containing an item
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextViews and ImageView in the item row
        TextView attractionName;
        TextView attractionAddress;
        TextView attractionYear;
        ImageView featureImage;

        MyViewHolder(View itemView) {
            super(itemView);
            attractionName = itemView.findViewById(R.id.attraction_name_text_view);
            attractionAddress = itemView.findViewById(R.id.attraction_location_text_view);
            attractionYear = itemView.findViewById(R.id.attraction_date_text_view);
            featureImage = itemView.findViewById(R.id.attraction_image);

            //Call setOnClick Listener on the itemView above
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRecyclerViewClickListener.onRecyclerViewItemClicked(v, this.getAdapterPosition());
        }

    }

    /**
     * This interface will be responsible for listening to clicks
     */
    public interface RecyclerViewClickListener {
        /**
         * @param view     is the view clicked on
         * @param position is the index of the view
         */
        void onRecyclerViewItemClicked(View view, int position);
    }
}
