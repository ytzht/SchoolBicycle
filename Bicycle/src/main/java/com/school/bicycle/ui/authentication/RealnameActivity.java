package com.school.bicycle.ui.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.CampusCardImage;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UTFXMLString;
import com.school.bicycle.global.UserService;
import com.school.bicycle.utils.CheckCardID;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class RealnameActivity extends BaseToolBarActivity {

    @BindView(R.id.rn_name)
    EditText rnName;
    @BindView(R.id.rn_idnumber)
    EditText rnIdnumber;
    @BindView(R.id.rn_idstudent)
    EditText rnIdstudent;
    @BindView(R.id.shangchuan)
    Button shangchuan;
    @BindView(R.id.rn_photo)
    ImageView rnPhoto;
    Bitmap photo;
    public static int isrealname = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realname);
        ButterKnife.bind(this);
        setToolbarText("实名认证");

    }

//    //获取照相返回的图片
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode== Activity.RESULT_CANCELED){
//
//        }else {
//            Uri uri = data.getData();
//            if (uri != null) {
//                photo = BitmapFactory.decodeFile(uri.getPath());
//                rnPhoto.setImageBitmap(photo);
//                saveImage(photo);
//            }
//            if (photo == null) {
//                Bundle bundle = data.getExtras();
//                if (bundle != null) {
//                    photo = (Bitmap) bundle.get("data");
//                    rnPhoto.setImageBitmap(photo);
//                    saveImage(photo);
//                } else {
//                    return;
//                }
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryBimap();
    }

    //保存并上传图片
    public void saveImage(Bitmap bmp) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            String saveDir = Environment.getExternalStorageDirectory()
                    + "/temple";
            File dir = new File(saveDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(saveDir, "temp.jpg");
            file.delete();
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);

            String url = getResources().getString(R.string.baseurl) + "user/campusCardImage";
            String cookie = new UserService(RealnameActivity.this).getCookie();
            OkHttpUtils.post()
                    .addHeader("cookie", cookie)
                    .addFile("image", "temp.jpg", file)
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            CampusCardImage campusCardImage = gson.fromJson(response, CampusCardImage.class);
                            if (campusCardImage.getCode() == 0) {
                                showShort(campusCardImage.getMsg());
                            } else {
                                showShort(campusCardImage.getMsg());
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 销毁图片文件
     */
    private void destoryBimap() {

        if (photo != null && !photo.isRecycled()) {
            photo.recycle();
            photo = null;
        }
    }


    //把AnimationDrawable中的图片资源逐个回收
    private void tryRecycleAnimationDrawable(
            AnimationDrawable animationDrawables) {
        if (animationDrawables != null) {
            animationDrawables.stop();
            for (int i = 0; i < animationDrawables.getNumberOfFrames(); i++) {
                Drawable frame = animationDrawables.getFrame(i);
                if (frame instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) frame).getBitmap();
                    bitmap.recycle();
                    bitmap = null;
                }
                frame.setCallback(null);
            }

            animationDrawables.setCallback(null);

        }
    }

    @OnClick({R.id.shangchuan, R.id.rn_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shangchuan:
                if (rnName.getText().toString().isEmpty()) {
                    showShort("姓名为空");
                } else {
                    if (rnIdnumber.getText().toString().isEmpty()) {
                        showShort("身份证号为空");
                    } else {
                        if (rnIdstudent.getText().toString().isEmpty()) {
                            showShort("学号为空");
                        } else {
                            if (rnIdnumber.getText().toString().length() != 18) {
                                showShort("请输入正确的身份证号");
                            } else if (!CheckCardID.isIDCard(rnIdnumber.getText().toString())) {
                                showShort("请输入正确的身份证号");
                            } else {

                                if (photoPath.equals("")) {
                                    showShort("请选择要上传的图片");
                                } else {

                                   goSubmit();




                                }
                            }
                        }
                    }
                }
                break;
            case R.id.rn_photo:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(true)
                        .start(RealnameActivity.this, PhotoPicker.REQUEST_CODE);

                break;
        }
    }

    private void goSubmit() {
        Log.d("name", UTFXMLString.getUTF8XMLString(rnName.getText().toString()));
        String cookie = new UserService(RealnameActivity.this).getCookie();
        String url = getResources().getString(R.string.baseurl) + "user/profile";

        OkHttpUtils
                .post()
                .url(url)
                .addHeader("cookie", cookie)
                .addParams("name", UTFXMLString.getUTF8XMLString(rnName.getText().toString()))
                .addParams("id_number", rnIdnumber.getText().toString())
                .addParams("campus_card_number", rnIdstudent.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                        showShort(baseResult.getMsg());
                        isrealname = 1;
                        goLuBan();
                    }
                });
    }

    private void goLuBan() {
        File file = new File(photoPath);
        Luban.get(this)
                .load(file)                     //传人要压缩的图片
                .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                .setCompressListener(new OnCompressListener() { //设置回调

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {

                        OkHttpUtils.post()
                                .addHeader("cookie", new UserService(RealnameActivity.this).getCookie())
                                .addFile("image", "temp.jpg", file)
                                .url(getResources().getString(R.string.baseurl) + "user/campusCardImage")
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        CampusCardImage campusCardImage = gson.fromJson(response, CampusCardImage.class);
                                        if (campusCardImage.getCode() == 0) {
                                            showShort(campusCardImage.getMsg());
                                        } else {
                                            showShort(campusCardImage.getMsg());
                                            finish();
                                        }

                                    }
                                });

                    }


                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();    //启动压缩

    }

    private String photoPath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photoPath = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS).get(0);
                Glide.with(this).load(photoPath).into(rnPhoto);
                L.d(photoPath);
            }
        }
    }


}
