package com.book.zhang.base.app;

import android.app.Application;
import android.content.res.Resources;


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
    public static Resources getAppResources() {
        return instans.getResources();
    }
    public static MyApp getApp() {
        return instans;
    }


}
