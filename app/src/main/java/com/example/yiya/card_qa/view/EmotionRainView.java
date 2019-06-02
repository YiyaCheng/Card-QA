package com.example.yiya.card_qa.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yiya.card_qa.bean.EmotionBean;

import java.util.Random;

/**
 * Created by Yiya on 2019/6/2.
 */

public class EmotionRainView extends View {
    private boolean isRaining = false;//表示表情雨是否开始
    private float mEmotionHeight,mEmotionWidth;//表情雨图标的高度和宽度
    private Context mContext;

    private Random mRandom;
    private EmotionBean mEmotionBean;
    private Matrix matrix;//用于缩放图标
    private Paint mPaint;//画笔

    public EmotionRainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mRandom = new Random();
        matrix = new Matrix();
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
    }

    public void startRain(final Bitmap bitmap){

        stopRain();

        setVisibility(VISIBLE);
        initData(bitmap);
        isRaining = true;
        invalidate();//整个控件再次执行onDraw
    }

    public void stopRain(){
        setVisibility(GONE);
        isRaining = false;
    }

    /**
     * 初始化数据bean
     * @param bitmap
     */
    private void initData(Bitmap bitmap) {
        mEmotionHeight = mEmotionWidth = dp2px(mContext,50);//初始化为50dp
        int maxDuration = 2000;
        EmotionBean bean = new EmotionBean();
        bean.bitmap = bitmap;
        bean.x = mRandom.nextInt(getWidth());
        bean.y = - (int)(Math.ceil(mEmotionHeight));
        //初始化速率
        float duration = mRandom.nextInt(500)+maxDuration;
        bean.velocityY = (int)(getHeight()*16/duration);//android系统每16毫秒发出重绘消息
        bean.velocityX = Math.round(mRandom.nextFloat());
        mEmotionBean = bean;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //表情雨已经落下或者初始化bean失败了
        if(!isRaining || mEmotionBean == null) {
            return;
        }
        EmotionBean emotion = mEmotionBean;
        Bitmap bitmap = emotion.bitmap;
        //表情雨落到屏幕之外
        if(mEmotionBean.bitmap.isRecycled() || mEmotionBean.y > getHeight()){
            return;
        }

        /*EmotionBean emotion = mEmotionBean;
        Bitmap bitmap = emotion.bitmap;

        if(bitmap.isRecycled() || isOutOfBottomBound()) {
            return;
        }*/

        matrix.reset();
        float heightScale = mEmotionHeight / bitmap.getHeight();
        float widthScale = mEmotionWidth / bitmap.getWidth();

        matrix.setScale(widthScale,heightScale);

        emotion.x = emotion.x + emotion.velocityX;
        emotion.y = emotion.y + emotion.velocityY;

        matrix.postTranslate(emotion.x, emotion.y);
        canvas.drawBitmap(bitmap,matrix,mPaint);

        postInvalidate();//一定要记得调用进行重绘

    }

    private boolean isOutOfBottomBound() {
        return mEmotionBean.y > getHeight();
    }

    /**
     * 将图标设置为统一宽高
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
