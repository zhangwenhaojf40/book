package com.book.zhang.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.book.zhang.base.R;
import com.book.zhang.base.app.BasePresent;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BaseActivity<P extends BasePresent> extends AppCompatActivity implements IView {
    public P mPresenter;
    public  Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        setToolBar();
        inJect();

        ImmersionBar
                .with(this)
                .statusBarColor(R.color.toolBar)
                .fitsSystemWindows(true)
                .init();
        initView();
        initListent();
        initData();
        attachView();

    }

    protected abstract void inJect();


    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    public void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        TextView mTitle = findViewById(R.id.tv_title);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }

    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initListent();

    protected abstract void initData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
