package com.book.zhang.base.http;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：ZWH
 * 创建日期： 2018/5/4 0004 on 下午 1:46
 * 描述说明：
 */

public class Transformer {
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
                    public void accept(@NonNull Disposable disposable) throws Exception {
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
