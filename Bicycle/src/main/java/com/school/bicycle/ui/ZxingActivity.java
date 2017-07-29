package com.school.bicycle.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.lockopen.LockOpenActivity;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZxingActivity extends BaseToolBarActivity {


    @BindView(R.id.inbiycle_num)
    LinearLayout inbiycleNum;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    private CaptureFragment captureFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        ButterKnife.bind(this);

        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

//        initView();
    }

    public static boolean isOpen = false;

//    private void initView() {
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear2);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isOpen) {
//                    CodeUtils.isLightEnable(true);
//                    isOpen = true;
//                } else {
//                    CodeUtils.isLightEnable(false);
//                    isOpen = false;
//                }
//
//            }
//        });
//    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//            bundle.putString(CodeUtils.RESULT_STRING, result);
//            resultIntent.putExtras(bundle);
//            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
//            ZxingActivity.this.finish();
            Log.d("result", result);
            String num = result.substring(result.indexOf("#") + 1);
            String location = getIntent().getStringExtra("location");
            Log.d("num", num);
            Log.d("location", location);
            startActivity(LockOpenActivity.class, "lock_code", num, "location", location);
            finish();

        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ZxingActivity.this.setResult(RESULT_OK, resultIntent);
            ZxingActivity.this.finish();
        }
    };

    @OnClick({R.id.inbiycle_num, R.id.linear2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inbiycle_num:
                String location = getIntent().getStringExtra("location");
                startActivity(openbynum_Activity.class, "location", location);
                finish();
                break;
            case R.id.linear2:
                if (!isOpen) {
                    CodeUtils.isLightEnable(true);
                    isOpen = true;
                } else {
                    CodeUtils.isLightEnable(false);
                    isOpen = false;
                }
                break;
        }
    }
}
