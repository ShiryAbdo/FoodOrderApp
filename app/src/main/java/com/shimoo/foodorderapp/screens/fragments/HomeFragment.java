package com.shimoo.foodorderapp.screens.fragments;

 import android.annotation.SuppressLint;
 import android.graphics.Color;
 import android.graphics.Typeface;
import android.os.Bundle;
 import android.os.Handler;
 import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.view.LayoutInflater;
import android.view.MenuItem;
 import android.view.MotionEvent;
 import android.view.View;
import android.view.ViewGroup;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.Toast;
//import android.widget.Toast;

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

    @BindView(R.id.toolbar_layout)CollapsingToolbarLayout collapsingToolbar ;
    @BindView(R.id.navigation)BottomNavigationView navigation ;
    @BindView(R.id.app_bar)AppBarLayout appBarLayout ;
    @BindView(R.id.layoutButton)LinearLayout layoutButton ;
    @BindView(R.id.recycler_view) RecyclerView recycler_view;
    @BindView(R.id.SearchEditText)EditText SearchEditText ;
    @BindView(R.id.SearchBt)ImageView SearchBt ;
    @Inject
    ApiService githubService;
    @Inject
    Picasso mPicasso;
    Call<Restaurants> reposCall;
    @Inject
    AdaptersRestaurant adapterRepos;
    HomeActivityComponent component ;
    List<Restaurants.RestaurantsBean> ChineseData = new ArrayList<>();
    List<Restaurants.RestaurantsBean> PizzaData = new ArrayList<>();
    List<Restaurants.RestaurantsBean> SeafoodData = new ArrayList<>();
    List<Restaurants.RestaurantsBean> BurgerData = new ArrayList<>();
    List<Restaurants.RestaurantsBean> KebabData = new ArrayList<>();

     private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener ;



    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View root=inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);
        getActivity().setTitle("Home");
        getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#31D896"));
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        BottomNavigationViewHelper.disableShiftMode(getActivity(),navigation);
        component= DaggerHomeActivityComponent.builder().mainHomeActivityModule(new MainHomeActivityModule(getActivity(),"Home")).componentInterFace(MyApplicationClass.get(getActivity()).getComponent()).build();
        component.injecHomeFragment(this);
        component.getAdaptersRestaurant();
        component.getApiService();
        getChinese("59","city", "25","ae5c9df3cbde209f3c36e8d9f7b47700");
        getBurgerData("59","city", "168","ae5c9df3cbde209f3c36e8d9f7b47700");
        getPizzaData("59","city", "82","ae5c9df3cbde209f3c36e8d9f7b47700");
        getSeafoodData("59","city", "83","ae5c9df3cbde209f3c36e8d9f7b47700");
        getKebabData("59","city", "178","ae5c9df3cbde209f3c36e8d9f7b47700");
        SearchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        SearchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBackSta();
                SearchEditText.setText("");
                getActivity().getFragmentManager().beginTransaction().add(R.id.mainHomeFragment, new SearchFragment(SearchEditText.getText().toString())).addToBackStack(null).commit();

            }
        });

        recycler_view.setAdapter(adapterRepos);
        adapterRepos.notifyDataSetChanged();
        mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ChineseFragment:
                        SendData(ChineseData);
                        return true;
                    case R.id.pizzaFragment:
                        SendData(PizzaData);
                        return true;
                    case R.id.SeafoodFragment:
                        SendData(SeafoodData);
                        return true;
                    case R.id.burgerFragment:
                        SendData(BurgerData);
                        return true;
                    case R.id.Kebab:
                        SendData(KebabData);
                        return true;





                }
                return false;


            }


        };



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
                    layoutButton.setPadding(0, 0, 0, 0);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
                    isShow = true;
                } else if (isShow) {
                     layoutButton.setPadding(0, 40, 0, 0);
                     collapsingToolbar.setTitle("" + "   ");
                     collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                     collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
                      isShow = false;
                }
            }
        });




        return root;
    }
    private void clearBackSta() {
             getFragmentManager().popBackStack();

    }

    private void getKebabData( String entity_id ,String entity_type , String cuisines, String user_key) {
        if(KebabData.isEmpty()){

            reposCall = githubService.getRestaurants(entity_id,entity_type, cuisines,user_key);
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    KebabData= restaurants.getRestaurants() ;
                    //                    getActivity().getFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment(ChineseData)).addToBackStack(null).commit();
                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void getChinese( String entity_id ,String entity_type , String cuisines, String user_key){
        if(ChineseData.isEmpty()){

            reposCall = githubService.getRestaurants(entity_id,entity_type, cuisines,user_key);
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    ChineseData= restaurants.getRestaurants() ;
                    adapterRepos.swapData(ChineseData ,getActivity());

                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    private void getBurgerData( String entity_id ,String entity_type , String cuisines, String user_key){
        if(BurgerData.isEmpty()){

            reposCall = githubService.getRestaurants(entity_id,entity_type, cuisines,user_key);
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    BurgerData= restaurants.getRestaurants() ;
                   }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    private void getPizzaData( String entity_id ,String entity_type , String cuisines, String user_key){
        if(PizzaData.isEmpty()){

            reposCall = githubService.getRestaurants(entity_id,entity_type, cuisines,user_key);
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    PizzaData= restaurants.getRestaurants() ;
                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    private void getSeafoodData( String entity_id ,String entity_type , String cuisines, String user_key){
        if(SeafoodData.isEmpty()){

            reposCall = githubService.getRestaurants(entity_id,entity_type, cuisines,user_key);
            reposCall.enqueue(new Callback<Restaurants>() {
                @Override
                public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                    Restaurants restaurants =  response.body();
                    SeafoodData= restaurants.getRestaurants() ;
                }

                @Override
                public void onFailure(Call<Restaurants> call, Throwable t) {
                    Toast.makeText(getActivity(),   t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    private void SendData(final List<Restaurants.RestaurantsBean> restaurantsBeans){
//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {


                adapterRepos.swapData(restaurantsBeans ,getActivity());
                adapterRepos.notifyDataSetChanged();

//            }
//        }, 1300);

    }



}