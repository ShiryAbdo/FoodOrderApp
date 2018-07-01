package com.shimoo.foodorderapp.modules;

import android.app.Activity;

import com.shimoo.foodorderapp.annotations.MainHomeActivityScope;
import com.shimoo.foodorderapp.screens.activities.MainHomeActivity;

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
}
