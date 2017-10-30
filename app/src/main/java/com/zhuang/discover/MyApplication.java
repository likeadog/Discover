package com.zhuang.discover;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhuang.discover.network.RetrofitHelper;

/**
 * Created by zhuang on 2017/10/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        RetrofitHelper.initialize(this);
    }
}