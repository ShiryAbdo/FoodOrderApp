package com.shimoo.foodorderapp.screens.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.helper.BottomNavigationViewHelper;
import com.shimoo.foodorderapp.screens.fragments.ChineseFragment;
import com.shimoo.foodorderapp.screens.fragments.DonutFragment;
import com.shimoo.foodorderapp.screens.fragments.TacosFragment;
import com.shimoo.foodorderapp.screens.fragments.BurgerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity{
    @BindView(R.id.toolbar)Toolbar toolbar ;
    @BindView(R.id.toolbar_layout)CollapsingToolbarLayout collapsingToolbar ;
    @BindView(R.id.app_bar)AppBarLayout appBarLayout ;
    @BindView(R.id.layoutSearch)LinearLayout layoutSearch ;
//    @BindView(R.id.tabhost) FragmentTabHost mTabHost;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ChineseFragment:
                    getSupportFragmentManager().beginTransaction().add(R.id.content, new ChineseFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.TacosFragment:
                    getSupportFragmentManager().beginTransaction().add(R.id.content, new TacosFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.DonutFragment:
                    getSupportFragmentManager().beginTransaction().add(R.id.content, new DonutFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.burgerFragment:
                    getSupportFragmentManager().beginTransaction().add(R.id.content, new BurgerFragment()).addToBackStack(null).commit();

                    return true;

            }
            return false;
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(ScrollingActivity.this,navigation);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    layoutSearch.setPadding(0, 70, 0, 0);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
//                    toolbar.setBackgroundColor(Color.parseColor("#0A3369"));
                    isShow = true;
                } else if (isShow) {
                    layoutSearch.setPadding(0, 0, 0, 0);

                    //carefull there should a space between double quote otherwise it wont work
                    collapsingToolbar.setTitle("" + "   ");
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.TransparentText);
                    //                    collapsingToolbar.setCollapsedTitleGravity(Gravity.BOTTOM);
//                    collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
                    isShow = false;
                }
            }
        });
    }
}
 