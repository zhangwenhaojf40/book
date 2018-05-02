package com.book.zhang.base.module;

import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class BookBean {




        /**
         * _id : 5a9669cb68c711471f287e32
         * title : 神级高玩
         * author : 罗素
         * longIntro : 一位游戏界高玩在一次游戏封测过程中，意外穿越到‘游戏’世界……为什么没有系统模板？为什么无法退出游戏？为什么npc这么真实？为什么我感觉自己真的杀了人？错觉！都是错觉！这就是个硬核向游戏，我就是个高玩，一个神级高玩！
         * majorCate : 玄幻
         * minorCate : 东方玄幻
         * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2230107%2F2230107_96c628806e08436dbadaca8ceecc4fa1.jpg%2F
         * hasCopyright : true
         * buytype : 0
         * contentType : txt
         * latelyFollower : 3552
         * wordCount : 239152
         * serializeWordCount : 6494
         * retentionRatio : 34.43
         * updated : 2018-04-17T12:07:16.253Z
         * chaptersCount : 107
         * lastChapter : 第105章 回门
         * isCollect : false
         * rating : {"count":36,"score":7.529,"isEffect":false}
         * tags : []
         * gender : ["male"]
         * copyright : 阅文集团正版授权
         */

        public String _id;
        public String title;
        public String author;
        public String longIntro;
        public String majorCate;
        public String minorCate;
        public String cover;
        public boolean hasCopyright;
        public int buytype;
        public String contentType;
        public int latelyFollower;
        public int wordCount;
        public int serializeWordCount;
        public String retentionRatio;
        public String updated;
        public int chaptersCount;
        public String lastChapter;
        public boolean isCollect;
        public RatingBean rating;
        public String copyright;
        public List<?> tags;
        public List<String> gender;

        public static class RatingBean {
            /**
             * count : 36
             * score : 7.529
             * isEffect : false
             */

            public int count;
            public double score;
            public boolean isEffect;
        }

}
