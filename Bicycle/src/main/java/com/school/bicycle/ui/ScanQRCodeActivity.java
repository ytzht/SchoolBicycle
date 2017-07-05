package com.school.bicycle.ui;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.zxing.Result;
import com.school.bicycle.utils.QrdecodeUtil;

/**
 * Created by Administrator on 2017/6/27.
 *
 * 二维码扫描
 */

public class ScanQRCodeActivity extends QrdecodeUtil
{
    @Override
    public void getRawResult(Result result) {
        showLong(result.toString());
        Log.d("result",result.toString());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri content_url;
//        if (result.toString().contains("http")) {
//            content_url = Uri.parse(result.toString());
//        } else {
//            content_url = Uri.parse("https://www.baidu.com/s?wd=" + result.toString());
//        }
//        intent.setData(content_url);
//        startActivity(intent);
//        finish();

    }
}
