package com.book.zhang.book.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.view.fragment.NewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class ItemDetailActivity extends BaseActivity {

    private ViewPager mViewPage;
    /*
    * 标题
    * */
    List<String> titls = new ArrayList<>();
     List<Fragment> fragments = new ArrayList<>();
    private String title;

    private TabLayout mTablayout;

    {
        titls.add("热门");
        titls.add("新书");
        titls.add("好评");


    }


    @Override
    protected void inJect() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_item_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        //标题
        title = intent.getStringExtra(MyResource.TITLE);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
        addFragments();

        mViewPage = findViewById(R.id.vp_view);
        mTablayout = findViewById(R.id.tabs);
        ItemAdapeter itemAdapeter = new ItemAdapeter(getSupportFragmentManager());
        mViewPage.setAdapter(itemAdapeter);
        mTablayout.setupWithViewPager(mViewPage);
        mTablayout.setTabsFromPagerAdapter(itemAdapeter);//给Tabs设置适配器
        mViewPage.setOffscreenPageLimit(3);
    }

    private void addFragments() {
        fragments.add(NewFragment.newInstans(title,"hot"));
        fragments.add(NewFragment.newInstans(title,"new"));
        fragments.add(NewFragment.newInstans(title,"reputation"));
    }
    @Override
    protected void initListent() {

    }

    @Override
    protected void initData() {

    }

     class ItemAdapeter extends FragmentPagerAdapter {
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titls.get(position);

        }

        public ItemAdapeter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titls.size();
        }
    }
}
