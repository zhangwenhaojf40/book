package com.book.zhang.base.http;

import android.util.Log;


import com.book.zhang.base.http.string.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class RetrofitManager {
    static RetrofitManager instans;
    private static final long DEFAULT_TIMEOUT = 10;//超时时间
    public static RetrofitManager newInstans() {
        synchronized (RetrofitManager.class) {
            if (instans == null) {
                synchronized (RetrofitManager.class) {
                    instans = new RetrofitManager();

                }
            }
        }
        return instans;
    }
    ApiServices http() {
        return initRetrofit().create(ApiServices.class);
    }
    public Retrofit initRetrofit() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("Http", "log: "+message );
            }
        });
        loggingInterceptor.setLevel(level);
        //拦截请求和响应日志并输出，其实有很多封装好的日志拦截插件，大家也可以根据个人喜好选择。
        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .
                        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new AddParamInterceptor())
                .build()
                ;
        return new Retrofit.Builder()

                .baseUrl(NetUrl.BASEURL)
//                字符串
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(StringConverterFactory.create())

//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MyConverterFactory.create())//可以添加自定义解析器和默认的解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加响应式编程的适配器
                .client(okHttpClient)//添加自定义的OKHttpClient对象
                .build();


    }
}
