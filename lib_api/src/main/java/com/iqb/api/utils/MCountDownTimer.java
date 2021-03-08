package com.iqb.api.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.iqb.api.R;
import com.iqb.api.base.app.ApiApplication;


public class MCountDownTimer extends CountDownTimer {

    private TextView mTextView;


    public MCountDownTimer(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        setClickable(false);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setText(millisUntilFinished / 1000 + "s 后重发"); // 设置倒计时时间
        setClickable(false);
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        setClickable(true);
    }
    public void setClickable(boolean clickable){
        if (clickable){
            mTextView.setClickable(true);// 重新获得点击
            mTextView.setTextColor(Color.parseColor("#FFBA15"));
            mTextView.setBackground(ApiApplication.getApplication().getResources().getDrawable(R.drawable.base_shape_yellow_hollow_rectangle));
        }
        else{
            mTextView.setClickable(false); // 设置不可点击
            mTextView.setTextColor(Color.parseColor("#BBBBBB"));
            mTextView.setBackground(null);

        }
    }
}
