package com.school.bicycle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.school.bicycle.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ytzht on 2017/07/24 下午10:08
 */

public class TimeCountDownTextView extends TextView {
    private static final long MAX_COUNTDOWN_TIME = 1000 * 600; // 1000 minutes
    private CountDownTimer mTimer = null;
    private String mCss;
    private long mCountDownTime;
    private long mSecond;
    private long mMinute;
    private onCountDownFinishListener mOnCountDownFinishListener;
    private onCountFinishListener onCountFinishListener;

    public TimeCountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCountDownTime(context, attrs);
    }

    public TimeCountDownTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCountDownTime(context, attrs);
    }

    public TimeCountDownTextView(Context context) {
        super(context);
    }

    private Context context;

    private void initCountDownTime(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray attribute = context.obtainStyledAttributes(attrs, R.styleable.TimeCountDownView);
        mCountDownTime = (long) attribute.getFloat(R.styleable.TimeCountDownView_countDownTime, 0);
        mCss = attribute.getString(R.styleable.TimeCountDownView_count_down_format);
        if (TextUtils.isEmpty(mCss)) {
            mCss = getContext().getString(R.string.count_down_default_format);
        }
    }

    public void setCountDownTimes(long countDownTime, String cssResId) {
        if (!TextUtils.isEmpty(cssResId)) {
            this.mCss = cssResId;
        }
        mCountDownTime = countDownTime;
    }

    public void setCountDownTimes(long countDownTime) {
        mCountDownTime = countDownTime;
    }

    public void start() {
        if (mCountDownTime < 0) {
            mCountDownTime = 0;
        } else {
            if (mCountDownTime > MAX_COUNTDOWN_TIME) {
                mCountDownTime = MAX_COUNTDOWN_TIME;
            }
        }
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            int countDownInterval = 1000;
            mTimer = new CountDownTimer(mCountDownTime, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mMinute = millisUntilFinished / (1000 * 60);
                    mSecond = (millisUntilFinished % (1000 * 60)) / 1000;
                    if (mMinute == 0 && mSecond == 0) {

                    } else {
                        TimeCountDownTextView.this.setText(Html.fromHtml(String.format(mCss, mMinute, mSecond)));
                    }
                }

                @Override
                public void onFinish() {
                    if (mOnCountDownFinishListener != null) {
                        mOnCountDownFinishListener.onFinish();
                    }
                }
            };
        }
        mTimer.start();
    }


    public void setOnCountDownFinishListener(onCountDownFinishListener onCountDownFinishListener) {
        this.mOnCountDownFinishListener = onCountDownFinishListener;
    }

    public void setOnCountFinishListener(onCountFinishListener onCountFinishListener) {
        this.onCountFinishListener = onCountFinishListener;
    }

    public interface onCountDownFinishListener {
        void onFinish();
    }

    public interface onCountFinishListener {
        void onTime(int ms);
    }
}