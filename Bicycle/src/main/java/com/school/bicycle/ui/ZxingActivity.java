package com.school.bicycle.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.lockopen.LockOpenActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.school.bicycle.ui.usebicycle.UseBicycleActivity;
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


    private CameraManager manager;
    private Camera camera = null;
    private Camera.Parameters parameters = null;


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


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Log.d("result", result);
            String num = result.substring(result.indexOf("#") + 1);
            String location = getIntent().getStringExtra("location");
            String status = getIntent().getStringExtra("status");
            Log.d("num", num);
            Log.d("location", location);
            Log.d("status", status);
            if (status.equals("0")){
                //跳回主界面
                Intent it = new Intent(ZxingActivity.this, MainActivity.class);
                it.putExtra("bike_number",num);
                new UserService(ZxingActivity.this).setShowOneMark("1");
                startActivity(it);
                finish();
            }else {
                startActivity(LockOpenActivity.class, "lock_code", num, "location", location);
                finish();
            }



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



    /**
     * 打开闪光灯
     *
     * @return
     */
    private void open() {
        try {
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭闪光灯
     *
     * @return
     */
    private void close() {
        try {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.release();
            camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.inbiycle_num, R.id.linear2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inbiycle_num:
                String location = getIntent().getStringExtra("location");
                String status = getIntent().getStringExtra("status");
                startActivity(openbynum_Activity.class, "location", location,"status",status);
//                finish();
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
