package com.book.zhang.book.view.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.book.zhang.base.app.BaseFragment;
import com.book.zhang.base.util.LoadingHelper;
import com.book.zhang.book.R;

import java.util.ArrayList;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class CategoryFragment extends BaseFragment {

    ArrayList<String> titls = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager mViewpage;
    private TabLayout tabs;

    private CategoryPageAdapter adapter;

    {
        titls.add("分类");
        titls.add("女生");
        titls.add("出版");

        for (int i =0;i<3;i++) {
            fragments.add( ManFragment.newInstans(titls.get(i)));
        }

    }

    @Override
    protected void inJect() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView(View view) {
        mViewpage = view.findViewById(R.id.vp_view);
        mViewpage.setOffscreenPageLimit(3);
        tabs = view.findViewById(R.id.tabs);
    }

    @Override
    protected void initListen() {

    }

    @Override
    protected void initData() {
        adapter = new CategoryPageAdapter(getFragmentManager());
        mViewpage.setAdapter(adapter);
        tabs.setupWithViewPager(mViewpage);//将TabLayout和ViewPager关联起来。
        tabs.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
    }

    @Override
    public void startLoad() {

    }

    @Override
    public void stopLoad() {


    }

    class CategoryPageAdapter extends FragmentStatePagerAdapter{

      @Nullable
      @Override
      public CharSequence getPageTitle(int position) {
          return titls.get(position);

      }

      public CategoryPageAdapter(FragmentManager fm) {
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
      @Override
      public int getItemPosition(Object object) {
          return POSITION_NONE;
      }
  }

}
