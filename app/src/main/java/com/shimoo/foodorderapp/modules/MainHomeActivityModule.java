package com.shimoo.foodorderapp.modules;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shimoo.foodorderapp.annotations.MainHomeActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainHomeActivityModule {

    private final Activity homeActivity;
     @MainHomeActivityScope
    public MainHomeActivityModule(Activity homeActivity) {
        this.homeActivity = homeActivity;
    }


    @Provides
    @MainHomeActivityScope
    public Activity provideHomeActivity(){
        return homeActivity;
    }
    @Provides
    public RecyclerView.LayoutManager providesLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
