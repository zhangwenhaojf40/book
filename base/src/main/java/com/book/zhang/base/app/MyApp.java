package com.book.zhang.base.app;

import android.app.Application;




/**
 * Created by ZhangWenHao
 * on 2018/4/26 0026.
 */

public class MyApp extends Application {
    public static MyApp instans;
    @Override
    public void onCreate() {
        super.onCreate();
        instans = this;

    }
    public static MyApp getApp() {
        return instans;
    }


}
