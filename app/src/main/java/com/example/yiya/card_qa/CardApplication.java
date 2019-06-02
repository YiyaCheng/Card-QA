package com.example.yiya.card_qa;

import android.app.Application;
import android.content.Context;

import com.example.yiya.card_qa.http.api.OkHttpUtil;

/**
 * Created by Yiya on 2019/6/2.
 */

public class CardApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init();
        mContext = this;

    }

}
