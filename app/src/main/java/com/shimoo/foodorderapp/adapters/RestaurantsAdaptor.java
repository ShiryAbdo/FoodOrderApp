package com.shimoo.foodorderapp.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.models.Restaurants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsAdaptor  extends RecyclerView.Adapter<RestaurantsAdaptor.ViewHolder> {
    private ArrayList<Restaurants.RestaurantsBean> androidList;
    private final Picasso mPicasso;


    @Inject
    public RestaurantsAdaptor(ArrayList<Restaurants.RestaurantsBean> android, Picasso mPicasso) {
        this.androidList = android;
        this.mPicasso = mPicasso;
    }

    @Override
    public RestaurantsAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. restaurants_row , viewGroup, false);


        return new RestaurantsAdaptor.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RestaurantsAdaptor.ViewHolder viewHolder, final int i) {
        mPicasso.load(androidList.get(i).getRestaurant().getThumb()).fit().into(viewHolder.restaurant_image);
        viewHolder.location.setText(androidList.get(i).getRestaurant().getLocation().getAddress());
        viewHolder.restaurant_name.setText(androidList.get(i).getRestaurant().getName());
        viewHolder.rating1Bar.setRating(Float.parseFloat(androidList.get(i).getRestaurant().getUser_rating().getAggregate_rating()));
        Drawable drawable = viewHolder.rating1Bar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);
        viewHolder.votes_number.setText("("+androidList.get(i).getRestaurant().getUser_rating().getVotes()+")");
        viewHolder.number.setText(androidList.get(i).getRestaurant().getAverage_cost_for_two());
    }





    @Override
    public int getItemCount() {
        return androidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.restaurant_image) ImageView restaurant_image;
        @BindView(R.id.number) TextView number;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.restaurant_name) TextView restaurant_name;
        @BindView(R.id.ratingBar) RatingBar rating1Bar;
        @BindView(R.id.votes_number)TextView votes_number ;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);



        }
    }



}
