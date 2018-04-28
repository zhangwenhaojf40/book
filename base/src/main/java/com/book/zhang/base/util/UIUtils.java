package com.book.zhang.base.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.book.zhang.base.app.MyApp;


/**
 * Created by Administrator
 * on 2017/3/2 0002.
 */
public class UIUtils {
    /**
     * activity跳转
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 指定任务栈
        getContext().startActivity(intent);

    }

    public static Context getContext() {
        return MyApp.getApp();
    }

    public static View getView(Context context, int layoutRes) {
        return View.inflate(context, layoutRes, null);
    }


    /***
     * 从父view 中移除当前view对象
     * @param view
     */
    public static void removeFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                // loadingpage 从父view 中移除  断绝关系
                group.removeView(view);
            }
        }
    }





    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
