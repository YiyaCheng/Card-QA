package com.example.yiya.card_qa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiya on 2019/6/1.
 */

public class QuestionInfo implements Serializable{
    public String question_id;
    public String title;//题目名称
    public String answer;//表示选项第1项还是第2项是对的
    public List<String> options = new ArrayList<String>();
    public int type;// 1表示答对，2表示答错
    public String option;//表示用户的做答是什么
}
