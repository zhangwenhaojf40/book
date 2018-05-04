package com.book.zhang.book.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.util.LoadingHelper;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.present.ReadActivityPresent;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class ReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_read);

        super.onCreate(savedInstanceState);

    }


}
