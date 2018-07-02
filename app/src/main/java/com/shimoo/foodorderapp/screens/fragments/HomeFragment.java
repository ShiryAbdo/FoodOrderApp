package com.shimoo.foodorderapp.screens.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.adapters.AdaptersRestaurant;
import com.shimoo.foodorderapp.components.DaggerHomeActivityComponent;
import com.shimoo.foodorderapp.components.HomeActivityComponent;
import com.shimoo.foodorderapp.controls.MyApplicationClass;
import com.shimoo.foodorderapp.helper.BottomNavigationViewHelper;
import com.shimoo.foodorderapp.helper.IFragmentToActivity;
import com.shimoo.foodorderapp.models.Restaurants;
import com.shimoo.foodorderapp.modules.MainHomeActivityModule;
import com.shimoo.foodorderapp.network.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement IFragmentToActivity");
//        }
//    }

    private IFragmentToActivity mCallback;
    @BindView(R.id.toolbar_layout)CollapsingToolbarLayout collapsingToolbar ;
    @BindView(R.id.app_bar)AppBarLayout appBarLayout ;
    @BindView(R.id.layoutSearch)LinearLayout layoutSearch ;
    @Inject
    ApiService githubService;
    @Inject
    Picasso mPicasso;
    Call<Restaurants> reposCall;
    @Inject
    AdaptersRestaurant adapterRepos;
    List<Restaurants.RestaurantsBean> githubRepos = new ArrayList<>();
    List<Restaurants.RestaurantsBean> TacosData = new ArrayList<>();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ChineseFragment:
                    getActivity().getFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.TacosFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new TacosFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.DonutFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new DonutFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.burgerFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new BurgerFragment()).addToBackStack(null).commit();

                    return true;

            }
            return false;
//            Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);

        }
    };

    private void showFragment(android.app.Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_news, container, false);
        mCallback = (IFragmentToActivity) getActivity();

        getActivity().getFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();
        ButterKnife.bind(this, root);
        BottomNavigationView navigation = (BottomNavigationView) root.findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(getActivity(),navigation);
        HomeActivityComponent component= DaggerHomeActivityComponent.builder()
                .mainHomeActivityModule(new MainHomeActivityModule(getActivity()))
                .componentInterFace(MyApplicationClass.get(getActivity()).getComponent())
                .build();
        component.getAdaptersRestaurant();
        component.injecHomeFragment(this);



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         collapsingToolbar.setTitle("");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                     layoutSearch.setPadding(0, 0, 0, 0);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
//                    toolbar.setBackgroundColor(Color.parseColor("#0A3369"));
                    isShow = true;
                } else if (isShow) {
                    layoutSearch.setPadding(0, 0, 0, 0);

                    //carefull there should a space between double quote otherwise it wont work
                    collapsingToolbar.setTitle("" + "   ");
//                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
                    //                    collapsingToolbar.setCollapsedTitleGravity(Gravity.BOTTOM);
//                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    isShow = false;
                }
            }
        });

        if(githubRepos.isEmpty()){

            reposCall = githubService.getRestaurants("59","city","25","ae5c9df3cbde209f3c36e8d9f7b47700");
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    githubRepos= restaurants.getRestaurants() ;
                    mCallback.sedData(githubRepos);
//                    Toast.makeText(getActivity(), restaurants.getResults_found()+" pp", Toast.LENGTH_SHORT).show();
                    if(!githubRepos.isEmpty())
                        adapterRepos.swapData(githubRepos ,getActivity());
                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        return root;
    }

}