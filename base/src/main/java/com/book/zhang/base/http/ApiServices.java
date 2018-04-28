package com.book.zhang.base.http;

import com.book.zhang.base.module.ManFragmentBean;
import com.book.zhang.base.module.ResultBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ZhangWenHao
 * on 2018/4/26 0026.
 */

public interface ApiServices {
    @GET("/api/classify")
    Observable<ResultBean<ManFragmentBean>> bookClassify();

}
