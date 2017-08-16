package com.school.bicycle.ui.Ivfriends;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class IvfriendsActivity extends BaseToolBarActivity {


    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.friends_num)
    EditText friendsNum;
    @BindView(R.id.submit_num)
    TextView submitNum;
    @BindView(R.id.relativeLayout5)
    RelativeLayout relativeLayout5;
    @BindView(R.id.tuijianma)
    TextView tuijianma;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.share_weixin)
    RelativeLayout shareWeixin;
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.share_qq)
    RelativeLayout shareQq;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.share_pengyouquan)
    RelativeLayout sharePengyouquan;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.share_weibo)
    RelativeLayout shareWeibo;
    @BindView(R.id.imageView15)
    ImageView imageView15;
    @BindView(R.id.share_kongjian)
    RelativeLayout shareKongjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ivfriends);
        ButterKnife.bind(this);
        setToolbarText("邀请好友");
        String num = getIntent().getStringExtra("num");
        tuijianma.setText("您的邀请码为：" + num);
    }


    @OnClick({R.id.submit_num, R.id.share_weixin, R.id.share_qq, R.id.share_pengyouquan, R.id.share_weibo, R.id.share_kongjian})
    public void onViewClicked(View view) {

        UMWeb web = new UMWeb("http://xiaoyixinggo.com/admin/supplier/account/app/dw.html");
        UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
        web.setTitle("校易行");//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("专注校园出行");//描述
        switch (view.getId()) {
//
            case R.id.submit_num:
                //提交介绍人号码
                if (!TextUtils.isEmpty(friendsNum.getText())) {
                    String url = Apis.Base + Apis.invite + friendsNum.getText().toString();
                    String cookie = new UserService(IvfriendsActivity.this).getCookie();
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addHeader("cookie", cookie)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    BaseResult baseResult = gson.fromJson(response, BaseResult.class);
//                                    if (baseResult.getCode()==1){
                                    showShort(baseResult.getMsg());
//                                    }else

                                }
                            });
                } else {
                    showShort("请输入好友手机号码");
                }


                break;
            case R.id.share_weixin:

                new ShareAction(IvfriendsActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.share_qq:
                new ShareAction(IvfriendsActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.share_pengyouquan:
                new ShareAction(IvfriendsActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.share_weibo:
                new ShareAction(IvfriendsActivity.this)
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.share_kongjian:
                new ShareAction(IvfriendsActivity.this)
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            showShort("分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(IvfriendsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(IvfriendsActivity.this, "取消分享", Toast.LENGTH_LONG).show();

        }
    };
}
