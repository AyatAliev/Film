package com.geektech.film.data.internet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static RetrofitService retrofitService;


    public static RetrofitService getService(){
        if (retrofitService == null) {
            retrofitService = builderRetrofit();
        }
        return retrofitService;
    }



    public static RetrofitService builderRetrofit() {
      return new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.class);
    }
}
