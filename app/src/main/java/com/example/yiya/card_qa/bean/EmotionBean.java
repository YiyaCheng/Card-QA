package com.example.yiya.card_qa.bean;

import android.graphics.Bitmap;

/**
 * Created by Yiya on 2019/6/2.
 * 表情雨bean
 */

public class EmotionBean {
    /**
     * 需要绘制的bitmap
     */
    public Bitmap bitmap;

    /**
     * x轴和y轴的坐标
     */
    public int x,y;

    /**
     * x轴和y轴的速率
     */
    public int velocityX,velocityY;
}
