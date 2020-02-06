package com.geektech.film.data.internet;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET()
    Call<JsonObject> fetchFilm(@Query("t") String name,
                               @Query("apikey") String apikey);

}
