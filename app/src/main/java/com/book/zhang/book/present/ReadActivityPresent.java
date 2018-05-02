package com.book.zhang.book.present;

import com.book.zhang.base.app.BasePresent;
import com.book.zhang.base.module.ReadBean;
import com.book.zhang.base.module.ResultBean;
import com.book.zhang.book.iview.IReadActivity;

import io.reactivex.Observable;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class ReadActivityPresent extends BasePresent<ReadBean,IReadActivity> {
   public  String bookId;
    @Override
    protected void paseJson(ReadBean body) {

    }

    @Override
    protected Observable<ResultBean<ReadBean>> getMethod() {
        return apiServices.bookChapters(bookId);
    }
}
