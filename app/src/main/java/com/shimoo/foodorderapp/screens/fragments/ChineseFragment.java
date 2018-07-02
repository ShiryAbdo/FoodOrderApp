package com.shimoo.foodorderapp.screens.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.adapters.AdaptersRestaurant;
import com.shimoo.foodorderapp.components.HomeActivityComponent;
import com.shimoo.foodorderapp.controls.MyApplicationClass;
import com.shimoo.foodorderapp.models.Restaurants;
import com.shimoo.foodorderapp.modules.MainHomeActivityModule;
import com.shimoo.foodorderapp.network.ApiService;
import com.shimoo.foodorderapp.screens.activities.MainHomeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChineseFragment extends Fragment {


    MainHomeActivity mainHomeActivity ;
    @Inject
    ApiService githubService;
    @Inject
    Picasso mPicasso;

    Call<List<Restaurants>> reposCall;

    // tell the Dagger to populate this field
    @Inject
    AdaptersRestaurant adapterRepos;
    HomeActivityComponent component ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View root =inflater.inflate(R.layout.fragment_chinese, container, false);

//
//        HomeActivityComponent component= DaggerFragmentcomponents.builder()
//                .mainHomeActivityModule(new MainHomeActivityModule(getActivity()))
//                .componentInterFace(MyApplicationClass.get(getActivity()).getComponent())
//                .build();
        return root ;

    }


}
