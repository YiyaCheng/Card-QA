package com.example.yiya.card_qa;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.TextView;

import com.example.yiya.card_qa.adapter.CardFragmentPagerAdapter;
import com.example.yiya.card_qa.bean.QuestionInfo;
import com.example.yiya.card_qa.presenter.TestPresenter;
import com.example.yiya.card_qa.view.EmotionRainView;
import com.example.yiya.card_qa.view.ITestView;

import java.util.List;

public class MainActivity extends FragmentActivity implements ITestView{

    private ViewPager viewpager;
    private TextView tvBottomText;
    private CardFragmentPagerAdapter mAdapter;
    private List<QuestionInfo> mList;//一般是网络获取，这里先造一个假数据
    private EmotionRainView emotion_rain_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        viewpager=(ViewPager)findViewById(R.id.viewpager);
        tvBottomText=(TextView)findViewById(R.id.tv_bottom_text);
        emotion_rain_view=(EmotionRainView)findViewById(R.id.emotionrain_view);

        TestPresenter presenter=new TestPresenter(this);
        presenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(emotion_rain_view != null){
            emotion_rain_view.stopRain();
        }
    }

    /**
     * 该方法作为presenter的回调
     * @param list
     */
    @Override
    public void updateUI(List<QuestionInfo> list) {
        mAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(3);//知道viewpager缓存3个页面
    }

    public void startRain() {
        emotion_rain_view.startRain(BitmapFactory.decodeResource(getResources(),R.mipmap.pic1));
    }

    @Override
    public void setBottomTipView(String count) {
        tvBottomText.setText("恭喜你累计答对"+count+"题");
    }
}
