package com.book.zhang.book.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.book.R;
import com.book.zhang.book.view.fragment.CategoryFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    ArrayList<Fragment> fragments;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private FragmentManager fm;


    @Override
    protected void inJect() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
    }

    @Override
    protected void initListent() {

    }

    @Override
    protected void initData() {
        fm.beginTransaction().add(R.id.fl_content, new CategoryFragment()).commit();
    }


}
