package com.book.zhang.base.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BaseFragment extends Fragment {
    public Context act;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (act == null) {
            act = getActivity();
        }
        View view = View.inflate(act, getLayoutRes(), null);
        initView(view);
        initListen();
        initData();
        return view;
    }

    protected abstract int getLayoutRes();

    protected abstract void initView( View view);


    protected abstract void initListen();

    protected abstract void initData();
}
