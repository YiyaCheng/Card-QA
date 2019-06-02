package com.example.yiya.card_qa.api;

import com.example.yiya.card_qa.CardContants;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.http.api.ApiUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取历史题目的api
 */
public class HistoryQuestionGetApi extends ApiUtil {

    public List<QuestionInfo> list = new ArrayList<>();

    @Override
    protected String getUrl() {
        return CardContants.URL+"/history";
    }

    @Override
    protected void parseData(JSONObject jsonObject) {
        try{
            JSONObject dataInfo = jsonObject.optJSONObject("data");
            JSONArray array = (JSONArray)dataInfo.get("history_list");
            if(list != null) {
                list.clear();
            }

            for (int i=0;i<array.length();i++) {
                QuestionInfo questionInfo = new Gson().fromJson(array.get(i).toString(),QuestionInfo.class);
                list.add(questionInfo);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //todo
    public void loadlocalData(JSONObject jsonObject) {
        try{
            JSONObject dataInfo = jsonObject.optJSONObject("data");
            JSONArray array = (JSONArray)dataInfo.get("history_list");
            if(list != null) {
                list.clear();
            }

            for (int i=0;i<array.length();i++) {
                QuestionInfo questionInfo = new Gson().fromJson(array.get(i).toString(),QuestionInfo.class);
                list.add(questionInfo);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
