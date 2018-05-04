package com.book.zhang.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：ZWH
 * 创建日期： 2018/5/4 0004 on 下午 3:08
 * 描述说明：
 */

public class Page extends View {


    public Page(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFCEC29C);
    }

}
