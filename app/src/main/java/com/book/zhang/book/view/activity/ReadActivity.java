package com.book.zhang.book.view.activity;

import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.present.ReadActivityPresent;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class ReadActivity extends BaseActivity<ReadActivityPresent> {
    @Override
    protected void inJect() {
        mPresenter = new ReadActivityPresent();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_read;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListent() {

    }

    @Override
    protected void initData() {
        mPresenter.bookId = getIntent().getStringExtra(MyResource.BOOKID);
        mPresenter.init();
    }
}
