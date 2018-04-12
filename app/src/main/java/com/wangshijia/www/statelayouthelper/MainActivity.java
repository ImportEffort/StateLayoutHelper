package com.wangshijia.www.statelayouthelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import tips.DefaultTipsHelper;
import tips.TipsHelper;


/**
 * @author wangshijia
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout tipsTestLayout;
    private DefaultTipsHelper tipsHelper;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tipsTestLayout = findViewById(R.id.tipsTestLayout);
        image = findViewById(R.id.image);

        Button loading = findViewById(R.id.loading);
        Button empty = findViewById(R.id.empty);
        Button error = findViewById(R.id.error);

        loading.setOnClickListener(this);
        empty.setOnClickListener(this);
        error.setOnClickListener(this);

        setTipView(tipsTestLayout);
//        setTipView(image);
    }


    public void setTipView(View view) {
        if (tipsHelper == null)
            tipsHelper = new DefaultTipsHelper(this, view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loading:
                getTipsHelper().showLoading(true);

                break;
            case R.id.empty:

                getTipsHelper().showEmpty();

                break;
            case R.id.error:

                getTipsHelper().showError(true, "网络异常", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getTipsHelper().showLoading(true);

                    }
                });

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getTipsHelper().hideLoading();
                    }
                },2000);
                break;
            default:
                break;
        }
    }


    //状态帧布局，通常用于网络请求的四种状态，普通、载入、错误、空白。支持Drawable或者View来展示，也可以混搭
    public TipsHelper getTipsHelper() {
        return tipsHelper;
    }
}
