package com.book.zhang.base.http;

import com.book.zhang.base.app.MyApp;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZhangWenHao
 * on 2017/8/17 0017.
 */

public abstract class BaseObsever<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public abstract void onNext(@NonNull T body) ;

    @Override
    public void onError(Throwable e) {
        onError();
        ApiErrorHelper.handleCommonError(MyApp.getApp(), e);
        e.printStackTrace();
    }

    protected abstract void onError();

    @Override
    public void onComplete() {

    }
}
