package com.book.zhang.base.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by ZhangWenHao on 2018/1/12 0012.
 */

public class GlideUtils {
    public static  void setGlide(Context act, ImageView iv, String url) {
        Glide
                .with(act) // 指定Context
                .load(url)// 指定图片的URL
                .diskCacheStrategy(DiskCacheStrategy.RESULT)

                .into(iv);//指定显示图片的ImageView
    }
}
