package com.shimoo.foodorderapp.components;

import com.shimoo.foodorderapp.adapters.RestaurantsAdaptor;
import com.shimoo.foodorderapp.annotations.MainHomeActivityScope;
import com.shimoo.foodorderapp.modules.MainHomeActivityModule;
import com.shimoo.foodorderapp.network.ApiService;
import com.shimoo.foodorderapp.screens.activities.MainHomeActivity;
import com.shimoo.foodorderapp.screens.fragments.ChineseFragment;
import com.shimoo.foodorderapp.screens.fragments.DonutFragment;
import com.shimoo.foodorderapp.screens.fragments.HomeFragment;
import com.shimoo.foodorderapp.screens.fragments.TacosFragment;
import com.shimoo.foodorderapp.screens.fragments.burgerFragment;

import dagger.Component;

@Component(modules = MainHomeActivityModule.class,
        dependencies = ComponentInterFace.class ) // Tell Dagger to use GithubApplicationComponent as a source for our modules
@MainHomeActivityScope
public interface MainHomeActivitycomponents {
//    AdapterRepos getAdapterRepos();
    RestaurantsAdaptor getRestaurantsAdaptor();
    ApiService getGithubService();

    // if return type is void, Dagger assumes that he has to inject something somewhere
    // so he will look into method parameters and try to inject there (here - HomeActivity) to @Inject
    void injectHomeActivity(MainHomeActivity homeActivity);
    void injectChineseFragment(ChineseFragment fragment);
    void injectTacosFragment(TacosFragment fragment);
    void injectDonutFragment(DonutFragment fragment);
    void injectburgerFragment(burgerFragment fragment);



}
