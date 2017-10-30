package com.zhuang.discover.network.service;

import com.zhuang.discover.model.Discover;
import com.zhuang.discover.network.ReturnDataList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhuang on 2017/10/26.
 */

public interface DiscoverService {

    @GET("discoverList.json")
    Call<ReturnDataList<Discover>> getDiscoverList();

}
