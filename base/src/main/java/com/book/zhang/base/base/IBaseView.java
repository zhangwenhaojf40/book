package com.book.zhang.base.base;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public interface IBaseView<T> extends IView {
    void getData(T data);
}
