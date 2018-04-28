package com.book.zhang.base.http;


import android.content.Context;

import com.book.zhang.base.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


/**
 * Created by Administrator on 2017/8/12/012.
 * <p>
 * 统一错误的处理类
 * <p>
 * http://www.jianshu.com/p/c105a4177982
 */

public class ApiErrorHelper {
    static String timeout = "连接超时";
    static String no = "未知错误";

    public static void handleCommonError(Context context, Throwable e) {

        if (e instanceof ConnectException) {


            ToastUtils.showToast(timeout);

        } else if (e instanceof SocketTimeoutException) {

            ToastUtils.showToast(timeout);

        } else if (e instanceof ApiException) {
            //ApiException处理
            ApiException exception = (ApiException) e;

            ToastUtils.showToast(timeout);

        } else {

            ToastUtils.showToast(no);
        }


    }
}


