package com.book.zhang.base.app;

import com.book.zhang.base.base.IView;
import com.book.zhang.base.http.ApiErrorHelper;
import com.book.zhang.base.http.ApiServices;
import com.book.zhang.base.http.RetrofitManager;
import com.book.zhang.base.module.ResultBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BasePresent<T,I extends IView> {



    public  ApiServices apiServices;
    public I mView;
    public void init() {
        if (mView != null) {

            mView.startLoad();
        }
        apiServices = RetrofitManager.newInstans().initRetrofit()
                .create(ApiServices.class);
        Observable<ResultBean<T>> observable=getMethod();
        observable
//               .compose(Transformer.switchSchedulers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean<T> tResultBean) {
                        if (mView != null) {

                            mView.stopLoad();
                        }
                        paseJson(tResultBean.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {

                            mView.stopLoad();
                        }

                        ApiErrorHelper.handleCommonError(MyApp.getApp(), e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    protected abstract void paseJson(T body);

    protected abstract Observable<ResultBean<T>> getMethod();

    public void attachView(I mView) {
        this.mView = mView;

    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }



}
