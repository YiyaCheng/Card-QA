package com.example.yiya.card_qa.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yiya.card_qa.MainActivity;
import com.example.yiya.card_qa.R;
import com.example.yiya.card_qa.api.QuestionSaveApi;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.http.api.ApiListener;
import com.example.yiya.card_qa.http.api.ApiUtil;
import com.example.yiya.card_qa.view.ButtonSelectView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.example.yiya.card_qa.R.id.tip_layout;


public class CardFragment extends Fragment {

    private View mRootView;
    private TextView contentTitle;
    /*private TextView tv_option1;
    private ImageView img_option1;
    private TextView tv_option2;
    private ImageView img_option2;*/
    private ButtonSelectView mButtonSelectView1;
    private ButtonSelectView mButtonSelectView2;
    private TextView TipContentTv;
    private LinearLayout tip_layout;

    private QuestionInfo mCurrentInfo;
    private MainActivity mainActivity;

    // 使用static定义方便后续直接调用
    public static CardFragment newInstance(QuestionInfo info) {
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",info);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_layout,container,false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mCurrentInfo = (QuestionInfo)getArguments().getSerializable("info");
        contentTitle.setText(mCurrentInfo.title);

        int type = mCurrentInfo.type;//type=1表示历史页面  type=2表示今天的题目
        int answer = Integer.valueOf(mCurrentInfo.answer);
        int userOption = 1;//默认用户选第一题
        if(!TextUtils.isEmpty(mCurrentInfo.option)) {
            userOption = Integer.valueOf(mCurrentInfo.option);
        }


        if(type == 1) {
            tip_layout.setVisibility(View.VISIBLE);
            TipContentTv.setText(mCurrentInfo.explain);
        }else{
            tip_layout.setVisibility(View.GONE);
        }

        //填充按钮控件中的文字
        mButtonSelectView1.setText(mCurrentInfo.options.get(0));
        mButtonSelectView2.setText(mCurrentInfo.options.get(1));

        mButtonSelectView1.setListenter(new ButtonSelectView.onButtonSelectClickListener() {
            @Override
            public void onClick() {
                saveOptionInfo(1);
            }
        });

        mButtonSelectView2.setListenter(new ButtonSelectView.onButtonSelectClickListener() {
            @Override
            public void onClick() {
                saveOptionInfo(2);
            }
        });


        if(type == 1) {
            if(answer == 1) {
                mButtonSelectView1.setIcon(R.mipmap.img_test_right);
                mButtonSelectView2.setIcon(R.mipmap.img_test_worn);
            }else{
                mButtonSelectView1.setIcon(R.mipmap.img_test_worn);
                mButtonSelectView2.setIcon(R.mipmap.img_test_right);
            }

            if(userOption == 1) {
                mButtonSelectView1.setSelect(true);
                mButtonSelectView2.setSelect(false);
            }else{
                mButtonSelectView1.setSelect(false);
                mButtonSelectView2.setSelect(true);
            }
        }
    }

    private void saveOptionInfo(final int option) {
        new QuestionSaveApi(mCurrentInfo.question_id,
                String.valueOf(option)).post(new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                QuestionSaveApi apiBase = (QuestionSaveApi)api;
                boolean isCorrect = ((QuestionSaveApi) api).mRankInfo.is_correct.equals("1");
                handleButtonSelectView(option,isCorrect);

                tip_layout.setVisibility(View.VISIBLE);
                TipContentTv.setText(mCurrentInfo.explain);
                mainActivity.setBottomTipView(apiBase.mRankInfo.correct_count);

            }

            @Override
            public void failure(ApiUtil api) {

            }
        });

        //todo
        /*String str = getFromAssets("submit.json", getContext());
        try{
            QuestionSaveApi apiBase = new QuestionSaveApi(mCurrentInfo.question_id,
                    String.valueOf(option));
            JSONObject jsonObject = new JSONObject(str);
            apiBase.loadLocalData(jsonObject);
            boolean isCorrect = apiBase.mRankInfo.is_correct.equals("1");
            handleButtonSelectView(option,isCorrect);

            tip_layout.setVisibility(View.VISIBLE);
            TipContentTv.setText(mCurrentInfo.explain);
            mainActivity.setBottomTipView(apiBase.mRankInfo.correct_count);
        }catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    /**
     * 获取本地的数据
     * @param fileName
     * @param context
     * @return
     */
    public String getFromAssets(String fileName,final Context context){
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)context;
    }

    /**
     * 处理当前的答题逻辑
     * @param option
     * @param isCorrect
     */
    private void handleButtonSelectView(int option, boolean isCorrect) {
        int rightOption;
        if(option == 1) {
            if(isCorrect) {
                rightOption = 1;
                mainActivity.startRain();//启动表情雨
            }else{
                rightOption = 2;
            }
            //如果用户选了第1题则第一个控件置为高亮
            mButtonSelectView1.setSelect(true);
            mButtonSelectView2.setSelect(false);
        }else{
            if(isCorrect) {
                rightOption = 2;
                mainActivity.startRain();
            }else{
                rightOption = 1;
            }
            mButtonSelectView1.setSelect(false);
            mButtonSelectView2.setSelect(true);
        }

        if(rightOption == 1) {
            mButtonSelectView1.setIcon(R.mipmap.img_test_right);
            mButtonSelectView2.setIcon(R.mipmap.img_test_worn);
        }else{
            mButtonSelectView1.setIcon(R.mipmap.img_test_worn);
            mButtonSelectView2.setIcon(R.mipmap.img_test_right);
        }

    }

    private void initView() {
        contentTitle = mRootView.findViewById(R.id.contentTitle);
        /*tv_option1 = mRootView.findViewById(R.id.tv_option1);
        tv_option2 = mRootView.findViewById(R.id.tv_option2);
        img_option1 = mRootView.findViewById(R.id.img_option1);
        img_option2 = mRootView.findViewById(R.id.img_option2);*/
        mButtonSelectView1 = mRootView.findViewById(R.id.first_option_layout);
        mButtonSelectView2 = mRootView.findViewById(R.id.second_option_layout);
        tip_layout = mRootView.findViewById(R.id.tip_layout);
        TipContentTv = mRootView.findViewById(R.id.tip_text);
    }

}
