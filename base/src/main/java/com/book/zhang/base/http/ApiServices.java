package com.book.zhang.base.http;

import com.book.zhang.base.module.BookBean;
import com.book.zhang.base.module.BookDetailBean;
import com.book.zhang.base.module.ManFragmentBean;
import com.book.zhang.base.module.ReadBean;
import com.book.zhang.base.module.ResultBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ZhangWenHao
 * on 2018/4/26 0026.
 */

public interface ApiServices {
    @GET("/api/classify")
    Observable<ResultBean<ManFragmentBean>> bookClassify();
    /*
    * 类型   标题
    * */
    @GET("/api/books")
    Observable<ResultBean<List<BookBean>>> books(@Query("type") String type,


                                               @Query("major") String major, @Query("page") int page);

    /**
     * 获取书籍信息
     *
     * @param bookId
     * @return
     */
    @GET(ModelPath.BOOKS + "/{bookId}")
    Observable<ResultBean<BookDetailBean>> bookInfo(@Path("bookId") String bookId);
    /**
     * 获取书籍目录
     *
     * @param bookId
     * @return
     */
    @GET(ModelPath.BOOKS + "/{bookId}/chapters")
    Observable<ResultBean<ReadBean>> bookChapters(@Path("bookId") String bookId);

}
