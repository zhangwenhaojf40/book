package com.book.zhang.base.module;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class ResultBean<T> {

    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

