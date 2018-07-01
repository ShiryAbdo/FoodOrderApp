package com.shimoo.foodorderapp.screens.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.helper.BottomNavigationViewHelper;
import com.shimoo.foodorderapp.screens.activities.ScrollingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    @BindView(R.id.toolbar_layout)CollapsingToolbarLayout collapsingToolbar ;
    @BindView(R.id.app_bar)AppBarLayout appBarLayout ;
    @BindView(R.id.layoutSearch)LinearLayout layoutSearch ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ChineseFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.TacosFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new TacosFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.DonutFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new DonutFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.burgerFragment:
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new burgerFragment()).addToBackStack(null).commit();

                    return true;

            }
            return false;
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_news, container, false);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();
        ButterKnife.bind(this, root);

        BottomNavigationView navigation = (BottomNavigationView) root.findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(getActivity(),navigation);
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();



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
        return root;
    }

}