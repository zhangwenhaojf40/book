package com.book.zhang.base.module;

import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/4/27 0027.
 */

public class ManFragmentBean {


    public List<MaleBean> male;
    public List<MaleBean> female;
    public List<MaleBean> press;

    public static class MaleBean {
        /**
         * icon : /images/icon/玄幻.png
         * monthlyCount : 14297
         * bookCount : 491899
         * name : 玄幻
         */

        public String icon;
        public int monthlyCount;
        public int bookCount;
        public String name;
    }




}
