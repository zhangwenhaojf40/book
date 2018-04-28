package com.book.zhang.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
        initListent();
        initData();
    }

    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initListent();

    protected abstract void initData();
}
