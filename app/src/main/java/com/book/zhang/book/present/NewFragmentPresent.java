package com.book.zhang.book.present;

import com.book.zhang.base.app.BasePresent;
import com.book.zhang.base.module.BookBean;
import com.book.zhang.base.module.ResultBean;
import com.book.zhang.base.util.LogUtils;
import com.book.zhang.book.iview.INewFragment;
import com.book.zhang.book.view.fragment.NewFragment;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class NewFragmentPresent extends BasePresent<List<BookBean>,INewFragment> {
    NewFragment fragment;


    public NewFragmentPresent(NewFragment fragment) {

        this.fragment = fragment;
    }

    @Override
    protected void paseJson(List<BookBean> data) {
        mView.getData(data);

    }

    @Override
    protected Observable<ResultBean<List<BookBean>>> getMethod() {
        return apiServices.books(fragment.type,fragment.title,1);
    }
}
