package com.school.bicycle.ui.authentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.CampusCardImage;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static me.iwf.photopicker.PhotoPicker.REQUEST_CODE;

public class RealnameActivity extends BaseToolBarActivity {

    @BindView(R.id.rn_name)
    EditText rnName;
    @BindView(R.id.rn_idnumber)
    EditText rnIdnumber;
    @BindView(R.id.rn_idstudent)
    EditText rnIdstudent;
    @BindView(R.id.rn_back)
    ImageView rnBack;
    @BindView(R.id.shangchuan)
    Button shangchuan;
    @BindView(R.id.rn_photo)
    ImageView rnPhoto;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realname);
        ButterKnife.bind(this);
        setToolbarText("实名认证");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        if (uri != null) {
            photo = BitmapFactory.decodeFile(uri.getPath());
            saveImage(photo,1);
        }
        if (photo == null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                photo = (Bitmap) bundle.get("data");
                saveImage(photo,1);
            } else {
                return;
            }
        }

    }


    public void saveImage(Bitmap bmp, int type) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "business");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "storephoto.jpg";

        String path = Environment.getExternalStorageDirectory() + "siness/" + fileName;
        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Log.d("", "saveImage: " + path);


            String url = getResources().getString(R.string.baseurl) + "user/campusCardImage";
            OkHttpUtils.post()//
                    .addFile("image", "messenger_01.png", file)
                    .url(url)
                    .build()//
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            CampusCardImage campusCardImage = gson.fromJson(response,CampusCardImage.class);
                            if (campusCardImage.getCode()==0){
                                showShort(campusCardImage.getMsg());
                            }else {
                                showShort("图片上传成功");
                                Log.d("url=",campusCardImage.get_Image_ur());
                            }
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.shangchuan, R.id.rn_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shangchuan:
                String url = getResources().getString(R.string.baseurl) + "user/profile";
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("name", rnName.getText().toString())
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
                            }
                        });
                break;
            case R.id.rn_photo:

                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, REQUEST_CODE);
                } else {

                }
                break;
        }
    }
}
