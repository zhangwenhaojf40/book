package com.book.zhang.base.http;


import com.book.zhang.base.util.PreferenceTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 目的是添加
 * 公共参数，请求头  追加到url后边    添加到请求体中
 * 1) Header
 * 2) Query Param
 * 3) POST Param form-data
 * 4) POST Param x-www-form-urlencoded
 */
public  class AddParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request build = chain.request().newBuilder()
                .addHeader("app-type", "Android")
                .addHeader("access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiMTIzIiwiaWF0IjoxNTI0NjQzOTg0LCJleHAiOjE1MjcyMzU5ODR9.qpLnAp4-rqc94fBxTH0up4zM4xxLNj-6qjd9rIvG9NE")
                .build();

        return chain.proceed(build);
    }




}

