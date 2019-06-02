package com.example.yiya.card_qa.api;

import com.example.yiya.card_qa.CardContants;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.http.api.ApiUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Yiya on 2019/6/2.
 * 获取当前题目的api
 */

public class GetQuestionInfoApi extends ApiUtil {
    public QuestionInfo mInfo;
    @Override
    protected String getUrl() {
        return CardContants.URL+"/getQuestion";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            JSONObject data =  jsonObject.optJSONObject("data");
            JSONObject info =  data.optJSONObject("info");

            mInfo = new Gson().fromJson(info.toString(),QuestionInfo.class);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //todo
    public void loadlocalData(JSONObject jsonObject) {
        try {
            JSONObject data =  jsonObject.optJSONObject("data");
            JSONObject info =  data.optJSONObject("info");

            mInfo = new Gson().fromJson(info.toString(),QuestionInfo.class);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
