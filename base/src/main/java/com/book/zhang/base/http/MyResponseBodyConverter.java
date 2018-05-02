package com.book.zhang.base.http;

import android.util.Log;

import com.book.zhang.base.module.ResultBean;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * MyResponseBodyConverter
 * Created by Administrator on 2017/8/11/011.
 */

/*public class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Log.e("bean", value.string());
        String response = value.string();
        Log.e("BeanJson", response);
        //Result2 类型是和服务器约定好的返回格式，再写接口的时候不必传递
        ResultBean re = gson.fromJson(response, ResultBean.class);
        //关注的重点，自定义响应码中非0的情况，一律抛出ApiException异常。
        // 这样，我们就成功的将该异常交给onError()去处理了。

        if (re.code != 10000) {
            value.close();

            throw new ApiException(re.code, re.msg);
        }

        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        //因为可能用到缓存，所以，需要吧整个数据都返回出去，同时Data里边也要是全部字段的格式
        ByteArrayInputStream bis = new ByteArrayInputStream(gson.toJson(re).getBytes());
        InputStreamReader reader = new InputStreamReader(bis, charset);
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }


    }


}*/
final class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
//        System.out.println(value.string());
        try {
            return adapter.read(jsonReader);
        } finally {

            value.close();
        }
    }
}
