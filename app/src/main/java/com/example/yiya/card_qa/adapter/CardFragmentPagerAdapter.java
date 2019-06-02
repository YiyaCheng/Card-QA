package com.example.yiya.card_qa.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.fragment.CardFragment;

import java.util.List;

/**
 * Created by Yiya on 2019/6/1.
 */

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<QuestionInfo> mList;

    public CardFragmentPagerAdapter(FragmentManager fm, List<QuestionInfo> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }
}
