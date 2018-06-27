package com.shimoo.foodorderapp.screens.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.screens.fragments.ChineseFragment;
import com.shimoo.foodorderapp.screens.fragments.DonutFragment;
import com.shimoo.foodorderapp.screens.fragments.TacosFragment;
import com.shimoo.foodorderapp.screens.fragments.burgerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity{
    @BindView(R.id.toolbar)Toolbar toolbar ;
    @BindView(R.id.toolbar_layout)CollapsingToolbarLayout collapsingToolbar ;
    @BindView(R.id.app_bar)AppBarLayout appBarLayout ;
    @BindView(R.id.layoutSearch)LinearLayout layoutSearch ;
    @BindView(R.id.tabhost) FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
            ButterKnife.bind(this);
         setSupportActionBar(toolbar);
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
                    collapsingToolbar.setTitle("Shimaa  ");
                     layoutSearch.setPadding(0,70,0,0);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
//                    toolbar.setBackgroundColor(Color.parseColor("#0A3369"));
                     isShow = true;
                } else if (isShow) {
                    layoutSearch.setPadding(0,0,0,0);

                    //carefull there should a space between double quote otherwise it wont work
                    collapsingToolbar.setTitle(""+ "   ");
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
                    //                    collapsingToolbar.setCollapsedTitleGravity(Gravity.BOTTOM);
//                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                     isShow = false;
                }
            }
        });

        // Create the tabs in main_fragment.xml
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);

        // Create Tab1 with a custom image in res folder
        mTabHost.addTab(mTabHost.newTabSpec("Chinese").setIndicator("Chinese", getResources().getDrawable(R.drawable.ic_noodles)),
                ChineseFragment.class, null);

        // Create Tab2
        mTabHost.addTab(mTabHost.newTabSpec("Tacos").setIndicator("Tacos", getResources().getDrawable(R.drawable.ic_taco)),
                TacosFragment.class, null);

        // Create Tab3
        mTabHost.addTab(mTabHost.newTabSpec("Burger").setIndicator("Burger", getResources().getDrawable(R.drawable.ic_hamburger_with_bacoon)),
                burgerFragment.class, null);
        // Create Tab4
        mTabHost.addTab(mTabHost.newTabSpec("Donut").setIndicator("Donut", getResources().getDrawable(R.drawable.ic_donut)),
                DonutFragment.class, null);
        // Create Tab4
        mTabHost.addTab(mTabHost.newTabSpec("Donut").setIndicator("Donut", getResources().getDrawable(R.drawable.ic_donut)),
                DonutFragment.class, null);
        // Create Tab4
        mTabHost.addTab(mTabHost.newTabSpec("Donut").setIndicator("Donut", getResources().getDrawable(R.drawable.ic_donut)),
                DonutFragment.class, null);


    }
}
