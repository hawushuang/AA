package com.aa.aa.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/5.
 */

public class RetrofitHelper {
    private volatile static RetrofitHelper instance;
    private Retrofit mRetrofit;

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    private RetrofitHelper() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
//                .addNetworkInterceptor(new NetworkInterceptor())
//                .addInterceptor(loggingInterceptor)
//                .cache(cache)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }


}
