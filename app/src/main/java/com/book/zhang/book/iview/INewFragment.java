package com.book.zhang.book.iview;

import com.book.zhang.base.base.IBaseView;
import com.book.zhang.base.module.BookBean;

import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public interface INewFragment extends IBaseView<List<BookBean>> {
    /*
    * 没有数据
    * */
    void empty();

}
