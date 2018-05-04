package com.book.zhang.book.present;

import com.book.zhang.base.app.BasePresent;
import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.module.BookDetailBean;
import com.book.zhang.base.module.ResultBean;
import com.book.zhang.book.iview.IBookDetailActivity;

import io.reactivex.Observable;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class BookDetailActivityPresent extends BasePresent<BookDetailBean,IBookDetailActivity> {
    public String bookID;


    @Override
    protected void paseJson(BookDetailBean body) {
        mView.getData(body);
    }

    @Override
    protected Observable<ResultBean<BookDetailBean>> getMethod() {
        return apiServices.bookInfo(bookID);
    }
}
