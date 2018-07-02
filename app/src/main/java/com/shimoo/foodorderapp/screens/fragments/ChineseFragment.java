package com.shimoo.foodorderapp.screens.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.adapters.AdaptersRestaurant;
import com.shimoo.foodorderapp.components.DaggerHomeActivityComponent;
import com.shimoo.foodorderapp.components.HomeActivityComponent;
import com.shimoo.foodorderapp.controls.MyApplicationClass;
import com.shimoo.foodorderapp.models.Restaurants;
import com.shimoo.foodorderapp.modules.MainHomeActivityModule;
import com.shimoo.foodorderapp.network.ApiService;
import com.shimoo.foodorderapp.screens.activities.MainHomeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChineseFragment extends Fragment {

    @BindView(R.id.recycler_view) RecyclerView recycler_view;

//    @BindView(R.id.repo_home_list)
//    ListView listView;

    MainHomeActivity mainHomeActivity ;
    @Inject
    ApiService githubService;
    @Inject
    Picasso mPicasso;

    Call<Restaurants> reposCall;

    // tell the Dagger to populate this field
    @Inject
    AdaptersRestaurant adapterRepos;
    HomeActivityComponent component ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View root =inflater.inflate(R.layout.fragment_chinese, container, false);
        ButterKnife.bind(this,root);


//
        HomeActivityComponent component= DaggerHomeActivityComponent.builder()
                .mainHomeActivityModule(new MainHomeActivityModule(getActivity()))
                .componentInterFace(MyApplicationClass.get(getActivity()).getComponent())
                .build();
        component.getAdaptersRestaurant();
        // Inject something here
        component.injectChineseFragment(this);

        // old way
//        adapterRepos = new AdapterRepos(this, mPicasso);
        // replaced by @Inject
//        adapterRepos=component.getAdapterRepos();
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.setAdapter(adapterRepos);

        // replaced by @Inject
        // we can also retrieve GithubService through our HomeActivityComponent instead of Application
//        githubService = component.getGithubService();

        reposCall = githubService.getRestaurants("59","city","25","ae5c9df3cbde209f3c36e8d9f7b47700");
        reposCall.enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                Restaurants restaurants =  response.body();

//                Toast.makeText(getActivity(), response.code() +"", Toast.LENGTH_SHORT).show();

//                Toast.makeText(getActivity(), restaurants.getRestaurants().size() +"", Toast.LENGTH_SHORT).show();
               if(!restaurants.getRestaurants().isEmpty())
                 adapterRepos.swapData(restaurants.getRestaurants() ,getActivity());

             }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {
                Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return root ;

    }


}
