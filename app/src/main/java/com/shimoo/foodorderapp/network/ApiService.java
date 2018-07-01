package com.shimoo.foodorderapp.network;

import com.shimoo.foodorderapp.models.Restaurants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
//https://developers.zomato.com/api/v2.1/search?entity_id=59&entity_type=city

public interface ApiService {
    @GET("api/v2.1/search?")
    Call<ArrayList<Restaurants>> getRestaurants(@Query("entity_id") String entity_id ,
                                                @Query("entity_type") String entity_type ,
                                                @Query("cuisines")String cuisines,
                                                @Header("user_key") String user_key);
}
