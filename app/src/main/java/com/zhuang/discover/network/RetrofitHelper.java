package com.zhuang.discover.network;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuang on 2016/12/30.
 */

public class RetrofitHelper {

    public static final String API_BASE_URL = "https://raw.githubusercontent.com/likeadog/discoverData/master/";
    private static Retrofit retrofit;

    public static void initialize(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
