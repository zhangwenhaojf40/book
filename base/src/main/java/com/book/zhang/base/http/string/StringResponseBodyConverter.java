package com.book.zhang.base.http.string;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    String response = "";
    @Override
    public String convert(ResponseBody value) throws IOException {

        try {
            response=value.string() ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            return response;
        } finally {
            value.close();
        }
    }
}