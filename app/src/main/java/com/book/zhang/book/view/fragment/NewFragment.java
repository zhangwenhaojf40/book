package com.book.zhang.book.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.book.zhang.base.app.BaseFragment;
import com.book.zhang.base.app.MyApp;
import com.book.zhang.base.http.NetUrl;
import com.book.zhang.base.module.BookBean;
import com.book.zhang.base.util.BaseUtils;
import com.book.zhang.base.util.GlideUtils;
import com.book.zhang.base.util.LoadingHelper;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.iview.INewFragment;
import com.book.zhang.book.present.NewFragmentPresent;
import com.book.zhang.book.view.activity.BookDetailActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class NewFragment extends BaseFragment<NewFragmentPresent> implements INewFragment {

    public String type;
    public String title;
    private RecyclerView mRecycleView;
    List<BookBean> datas = new ArrayList<>();
    private ItemAdapeter adapeter;

    @Override
    protected void inJect() {
        mPresenter = new NewFragmentPresent( this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView(View view) {
        mRecycleView = view.findViewById(R.id.rv_view);
        title = getArguments().getString(MyResource.TITLE);
        type = getArguments().getString(MyResource.TYPE);
        initAdapter();
    }
    /*
    * 初始化 Adapeter
    * */
    private void initAdapter() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(act));
        adapeter = new ItemAdapeter(R.layout.item_bookinfo,datas);
        mRecycleView.setAdapter(adapeter);
    }

    @Override
    protected void initListen() {

            adapeter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(act,BookDetailActivity.class);

                    intent.putExtra(MyResource.BOOKID, datas.get(position)._id);
                    if (android.os.Build.VERSION.SDK_INT > 20) {
                        ImageView imageView = view.findViewById(R.id.book_brief_iv_portrait);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView, "bookImage").toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            });
    }

    @Override
    protected void initData() {

         mPresenter.init();
    }

    @Override
    public void empty() {
        adapeter.setEmptyView(R.layout.layout_empty);
    }

    @Override
    public void startLoad() {
        LoadingHelper.getInstance().showLoading(context);
    }

    @Override
    public void stopLoad() {
        LoadingHelper.getInstance().hideLoading();
    }

    class ItemAdapeter extends BaseQuickAdapter<BookBean,BaseViewHolder> {

        public ItemAdapeter(int layoutResId, @Nullable List<BookBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BookBean item) {

            String wordCount = item.latelyFollower / 10000 > 0 ? BaseUtils.format1Digits((double)item.latelyFollower / 10000 ) + "万" : item.latelyFollower + "";

            helper.setText(R.id.book_brief_tv_title, item.title)
                    .setText(R.id.book_brief_tv_author, item.author + "  |  " + item.majorCate)
                    .setText(R.id.book_brief_tv_brief, item.longIntro)
                    .setText(R.id.ctv_arrow_count, wordCount + "")
                    .setText(R.id.ctv_retention, item.retentionRatio + "%");


            GlideUtils.setGlide(MyApp.getApp(),helper.getView(R.id.book_brief_iv_portrait),
                    NetUrl.IMAGE_URL+item.cover
                    );

        }
    }
  public static Fragment newInstans(String title, String  type) {
      NewFragment fragment = new NewFragment();
      Bundle bundle = new Bundle();
      bundle.putString(MyResource.TITLE, title);
      bundle.putString(MyResource.TYPE, type);
      fragment.setArguments(bundle);
      return fragment;


  }

    @Override
    public void getData(List<BookBean> data) {
        if (data == null || data.size() == 0) {//说明是空的
            empty();
        } else {
            datas.clear();
            datas.addAll(data);
            adapeter.notifyDataSetChanged();
        }
    }
}
