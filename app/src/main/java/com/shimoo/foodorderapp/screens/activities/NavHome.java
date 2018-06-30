package com.shimoo.foodorderapp.screens.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shimoo.foodorderapp.R;
import com.shimoo.foodorderapp.screens.fragments.FeedFragment;
import com.shimoo.foodorderapp.screens.fragments.MessagesFragment;
import com.shimoo.foodorderapp.screens.fragments.MusicFragment;
import com.shimoo.foodorderapp.screens.fragments.NewsFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class NavHome extends AppCompatActivity  {
    SNavigationDrawer sNavigationDrawer;
    int color1=0;
    Class fragmentClass;
    public static Fragment fragment;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
         sNavigationDrawer.setAppbarTitleTV("SHIMAA");

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem( "Shimaa Abdo",R.drawable.user_icon));
        menuItems.add(new MenuItem( "Shimaa Abdo",R.drawable.user_icon));
        menuItems.add(new MenuItem("Home",R.drawable.news_bg));
        menuItems.add(new MenuItem("My Orders",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Notifications",R.drawable.message_bg));
        menuItems.add(new MenuItem("Log out",R.drawable.music_bg));
         sNavigationDrawer.setMenuItemList(menuItems);
        View header = (View)getLayoutInflater().inflate(R.layout.nav_header_main2,null);
//        mDrawerListView.addHeaderView(header);
        sNavigationDrawer.onViewAdded(header);

//        fragmentClass =  NewsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }


        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
//                        color1 = R.color.red;
//                        fragmentClass = NewsFragment.class;
                        break;
                    }
                    case 1:{
                        color1 = R.color.orange;
                        fragmentClass = FeedFragment.class;
                        break;
                    }
                    case 2:{
                        color1 = R.color.green;
                        fragmentClass = MessagesFragment.class;
                        break;
                    }
                    case 3:{
                        color1 = R.color.blue;
                        fragmentClass = MusicFragment.class;
                        break;
                    }

                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }
}