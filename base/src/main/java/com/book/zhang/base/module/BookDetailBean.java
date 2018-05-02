package com.book.zhang.base.module;

import java.util.List;

/**
 * Created by ZhangWenHao
 * on 2018/5/2 0002.
 */

public class BookDetailBean {

    /**
     * _id : 5ab0c8083dfa517ad086a09e
     * title : 重生之白首不相离
     * author : 熊猫胖大
     * longIntro : 重生了，一个似是而非的世界。这个世界没有李白、没有杜甫、没有李清照、没有纳兰性德……这个世界没有黄霑、没有李宗盛、没有罗大佑、没有方文山……这个世界没有卡梅隆、没有斯皮尔伯格、没有卢卡斯、没有诺兰……这个世界没有《赌神》、没有《大话西游》、没有《野蛮女友》、没有《战狼》……这个世界没有金庸、没有古龙、没有黄易、没有网文大神……这个世界没有《七龙珠》、没有《圣斗士》、没有《机器猫》、没有《海贼王》……看着躺在病床上的未婚妻，马一诺感受到了来自于灵魂深处的爱恋和责任。“我会陪着你，白首不相离。”抚摸着未婚妻的脸颊，马一诺许下了承诺。
     * majorCate : 都市
     * minorCate : 都市生活
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2253609%2F2253609_534cfc6bd20e474bbcc49a6966e5370b.jpg%2F
     * hasCopyright : true
     * buytype : 0
     * contentType : txt
     * latelyFollower : 5174
     * wordCount : 173613
     * serializeWordCount : 4327
     * retentionRatio : 43.38
     * updated : 2018-04-17T11:40:15.733Z
     * chaptersCount : 65
     * lastChapter : 第65章 主从异位
     * isCollect : false
     * rating : {"count":82,"score":7.618,"isEffect":true}
     * tags : []
     * gender : ["male"]
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
    public List<?> tags;
    public List<String> gender;

    public static class RatingBean {
        /**
         * count : 82
         * score : 7.618
         * isEffect : true
         */

        public int count;
        public double score;
        public boolean isEffect;
    }
}
