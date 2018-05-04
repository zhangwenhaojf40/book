package com.book.zhang.base.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.book.zhang.base.base.IView;
import com.book.zhang.base.util.LoadingHelper;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public abstract class BaseFragment<P extends BasePresent> extends RxFragment implements IView {
    public Context act;
    public P mPresenter;
    public Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (act == null) {
            act = getActivity();
        }
        inJect();
        attachView();
        View view = View.inflate(act, getLayoutRes(), null);
        initView(view);
        initListen();
        initData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);

    }

    protected abstract void inJect();

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    protected abstract int getLayoutRes();

    protected abstract void initView( View view);


    protected abstract void initListen();

    protected abstract void initData();


}
