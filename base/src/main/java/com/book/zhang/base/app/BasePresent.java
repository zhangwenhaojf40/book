package com.book.zhang.base.app;

import com.book.zhang.base.http.ApiServices;
import com.book.zhang.base.http.BaseObsever;

import com.book.zhang.base.http.RetrofitManager;
import com.book.zhang.base.module.ResultBean;
import com.book.zhang.base.util.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BasePresent<T> {

    public  ApiServices apiServices;

    public void init() {
        apiServices = RetrofitManager.newInstans().initRetrofit()
                .create(ApiServices.class);
        Observable<ResultBean<T>> observable=getMethod();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObsever<ResultBean<T>>() {
                    @Override
                    public void onNext(ResultBean<T> body) {
                        if (body.code == 10000) {

                            paseJson(body.data);
                        } else {
                            ToastUtils.showToast("请求异常");
                        }

                    }

                    @Override
                    protected void onError() {

                    }
                });

    }

    protected abstract void paseJson(T body);

    protected abstract Observable<ResultBean<T>> getMethod();





}
