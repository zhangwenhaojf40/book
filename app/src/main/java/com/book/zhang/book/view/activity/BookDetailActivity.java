package com.book.zhang.book.view.activity;

import android.content.Intent;
import android.widget.TextView;

import com.book.zhang.base.base.BaseActivity;
import com.book.zhang.base.http.NetUrl;
import com.book.zhang.base.module.BookDetailBean;
import com.book.zhang.base.util.GlideUtils;
import com.book.zhang.base.util.LoadingHelper;
import com.book.zhang.base.util.MyResource;
import com.book.zhang.book.R;
import com.book.zhang.book.iview.IBookDetailActivity;
import com.book.zhang.book.present.BookDetailActivityPresent;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class BookDetailActivity extends BaseActivity<BookDetailActivityPresent> implements IBookDetailActivity{

    private String bookId;
    private TextView tvName;
    private TextView tvAuthor;
    private TextView tvType;
    private TextView tvCount;
    private TextView tvFow;
    private TextView tvGood;

    @Override
    protected void inJect() {
        mPresenter = new BookDetailActivityPresent();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        bookId = intent.getStringExtra(MyResource.BOOKID);
        mPresenter.bookID = bookId;
        findId();
    }

    private void findId() {
        tvName =  findViewById(R.id.tv_book_name);
        tvAuthor =  findViewById(R.id.ctv_book_author);
        tvType =  findViewById(R.id.tv_book_classify);
        tvCount =  findViewById(R.id.tv_word_count);
        tvFow =  findViewById(R.id.tv_fow_num);
        tvGood =  findViewById(R.id.tv_good_num);

    }

    @Override
    protected void initListent() {
        findViewById(R.id.tv_read).setOnClickListener(view->{
            Intent intent = new Intent(this,ReadActivity.class);

            intent.putExtra(MyResource.BOOKID, bookId);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        mPresenter.init();
    }



    @Override
    public void getData(BookDetailBean data) {
        //图片
        GlideUtils.setGlide(this,findViewById(R.id.iv_book_image),NetUrl.IMAGE_URL+data.cover);
        //书名
        tvName .setText(data.title);
        //作者
        tvAuthor.setText(data.author);
        //书的类型
        tvType.setText("  |  " + data.majorCate);
        //文字量
        tvCount.setText(data.serializeWordCount+ "");
        //收藏量
        tvFow.setText(data.latelyFollower+ "");
        //好评率
        tvGood.setText(data.retentionRatio + "%");

    }

    @Override
    public void startLoad() {
        LoadingHelper.getInstance().showLoading(this);
    }

    @Override
    public void stopLoad() {
        LoadingHelper.getInstance().hideLoading();

    }
}
