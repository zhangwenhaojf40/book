package com.book.zhang.book.present;

import android.app.Activity;

import com.book.zhang.base.app.BasePresent;
import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.http.ApiServices;
import com.book.zhang.base.http.BaseObsever;
import com.book.zhang.base.http.RetrofitManager;
import com.book.zhang.base.module.ManFragmentBean;
import com.book.zhang.base.module.ResultBean;
import com.book.zhang.book.iview.IManFragment;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class ManFragmnetPresent extends BasePresent<ManFragmentBean,IManFragment> {


    @Override
    protected void paseJson(ManFragmentBean body) {
        mView.getData(body);
    }

    @Override
    protected Observable<ResultBean<ManFragmentBean>> getMethod() {
        return apiServices.bookClassify();
    }


}
