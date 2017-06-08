package com.school.bicycle.http;

import android.content.Context;

import com.school.bicycle.global.Constant;
import com.school.bicycle.global.L;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by zht on 2017/04/19 17:21
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context, boolean show) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true, show);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
//        L.d("ProgressSubscriber onStart");
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
//        L.d("ProgressSubscriber onCompleted");
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        L.e(e.toString());
        if (e instanceof SocketTimeoutException) {
            if (mSubscriberOnNextListener != null) {
                mSubscriberOnNextListener.onError(Constant.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof ConnectException) {
            if (mSubscriberOnNextListener != null) {
                mSubscriberOnNextListener.onError(Constant.NETERROR, "网络中断，请检查您的网络状态");
            }
        } else if (e instanceof APIException) {
            if (mSubscriberOnNextListener != null) {
                mSubscriberOnNextListener.onError(((APIException) e).getCode(), e.getMessage());
            }
        } else {
            if (mSubscriberOnNextListener != null) {
                mSubscriberOnNextListener.onError(Constant.UNKONWERROR, e.getMessage());
            }
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
//        L.d("ProgressSubscriber onNext");
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
//        L.d("ProgressSubscriber onCancelProgress");
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
