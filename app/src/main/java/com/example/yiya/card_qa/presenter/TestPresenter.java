package com.example.yiya.card_qa.presenter;

import android.content.Context;

import com.example.yiya.card_qa.api.GetQuestionInfoApi;
import com.example.yiya.card_qa.api.HistoryQuestionGetApi;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.http.Util.Util;
import com.example.yiya.card_qa.view.ITestView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiya on 2019/6/2.
 */

public class TestPresenter {
    private ITestView mITestView;
    private Context mContext;
    private List<QuestionInfo> mList;
    private List<QuestionInfo> mHistoryList;
    private QuestionInfo mCurrentInfo;

    public TestPresenter(ITestView iTestView){
        mContext=(Context)iTestView;
        mITestView=iTestView;
    }

    public void getData(){
        //已经获取到所有data,放到一个List<QuestionInfo>
        //mITestView.updateUI(mList);
        getHistory();
    }

    private void getHistory(){
//        new HistoryQuestionGetApi().get(mContext, new ApiListener() {
//            @Override
//            public void success(ApiUtil api) {
//                HistoryQuestionGetApi apiBase = (HistoryQuestionGetApi)api;
//                mHistoryList = apiBase.list;
//                getCurrentQuestionApi();
//            }
//
//            @Override
//            public void failure(ApiUtil api) {
//
//            }
//        });




    }


}
