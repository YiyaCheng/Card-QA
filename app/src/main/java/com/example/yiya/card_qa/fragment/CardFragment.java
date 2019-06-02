package com.example.yiya.card_qa.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yiya.card_qa.MainActivity;
import com.example.yiya.card_qa.R;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.view.ButtonSelectView;


public class CardFragment extends Fragment {

    private View mRootView;
    private TextView contentTitle;
    /*private TextView tv_option1;
    private ImageView img_option1;
    private TextView tv_option2;
    private ImageView img_option2;*/
    private ButtonSelectView mButtonSelectView1;
    private ButtonSelectView mButtonSelectView2;

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
    }

    private void initView() {
        contentTitle = mRootView.findViewById(R.id.contentTitle);
        /*tv_option1 = mRootView.findViewById(R.id.tv_option1);
        tv_option2 = mRootView.findViewById(R.id.tv_option2);
        img_option1 = mRootView.findViewById(R.id.img_option1);
        img_option2 = mRootView.findViewById(R.id.img_option2);*/
        mButtonSelectView1 = mRootView.findViewById(R.id.first_option_layout);
        mButtonSelectView2 = mRootView.findViewById(R.id.second_option_layout);
    }

}
