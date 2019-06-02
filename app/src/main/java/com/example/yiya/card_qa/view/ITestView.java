package com.example.yiya.card_qa.view;

import com.example.yiya.card_qa.bean.QuestionInfo;

import java.util.List;

/**
 * Created by Yiya on 2019/6/2.
 */

public interface ITestView {
    void updateUI(List<QuestionInfo> list);//用于更新UI
    void setBottomTipView(String count);//count表示答对多少题
}
