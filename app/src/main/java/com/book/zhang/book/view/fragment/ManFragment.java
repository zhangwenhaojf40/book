package com.book.zhang.book.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.book.zhang.base.app.BaseFragment;
import com.book.zhang.base.app.MyApp;
import com.book.zhang.base.http.NetUrl;
import com.book.zhang.base.module.ManFragmentBean;
import com.book.zhang.base.util.GlideUtils;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.iview.IManFragment;
import com.book.zhang.book.present.ManFragmnetPresent;
import com.book.zhang.book.view.activity.ItemDetailActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class ManFragment extends BaseFragment<ManFragmnetPresent> implements IManFragment {


    private RecyclerView mRecycleView;
    List<ManFragmentBean.MaleBean> datas = new ArrayList<>();

    private ManFragmentAdapter adapter;
    private  String title;
    /*
    * 类型
    * */
    private String type="male";

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_man;
    }

    @Override
    protected void initView(View view) {
         title = getArguments().getString("title");
        mRecycleView = view.findViewById(R.id.rv_view);
        adapter = new ManFragmentAdapter(R.layout.item_classify, datas);
        mRecycleView.setLayoutManager(new LinearLayoutManager(act));

        mRecycleView.setAdapter(adapter);
    }

    @Override
    protected void initListen() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(act, ItemDetailActivity.class);
                String title = datas.get(position).name;
                intent.putExtra(MyResource.TYPE, type);
                intent.putExtra(MyResource.TITLE, title);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new ManFragmnetPresent();
        mPresenter.init();
    }

    @Override
    public void getData(ManFragmentBean data) {


        datas.clear();
        switchTo(data);
    }

    static class ManFragmentAdapter extends BaseQuickAdapter<ManFragmentBean.MaleBean, BaseViewHolder> {


        public ManFragmentAdapter(int layoutResId, @Nullable List<ManFragmentBean.MaleBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ManFragmentBean.MaleBean item) {
            helper.setText(R.id.tv_name, item.name)
                    .setText(R.id.tv_count, item.bookCount + "本");

            GlideUtils.setGlide(MyApp.getApp(), helper.getView(R.id.iv_icon), NetUrl.BASEURL + item.icon);
        }
    }

    public void switchTo(ManFragmentBean data) {


        switch (title) {
            case "分类":
                datas.addAll(data.male);
                type = "male";
                break;
            case "女生":
                datas.addAll(data.female);
                type = "female";
                break;
            case "出版":
                datas.addAll(data.press);
                type = "press";
                break;
        }

        adapter.notifyDataSetChanged();
    }

    public static ManFragment newInstans(String titles) {
        Bundle bundle = new Bundle();
        bundle.putString("title", titles);
        ManFragment manFragment = new ManFragment();
        manFragment.setArguments(bundle);


        return manFragment;

    }
}
