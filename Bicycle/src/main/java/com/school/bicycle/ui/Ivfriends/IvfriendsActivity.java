package com.school.bicycle.ui.Ivfriends;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IvfriendsActivity extends BaseToolBarActivity {

    @BindView(R.id.friends_num)
    EditText friendsNum;
    @BindView(R.id.submit_num)
    TextView submitNum;
    @BindView(R.id.tuijianma)
    TextView tuijianma;
    @BindView(R.id.share_weixin)
    RelativeLayout shareWeixin;
    @BindView(R.id.share_qq)
    RelativeLayout shareQq;
    @BindView(R.id.share_pengyouquan)
    RelativeLayout sharePengyouquan;
    @BindView(R.id.share_weibo)
    RelativeLayout shareWeibo;
    @BindView(R.id.share_kongjian)
    RelativeLayout shareKongjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ivfriends);
        ButterKnife.bind(this);
        setToolbarText("邀请好友");
    }

    @OnClick({R.id.submit_num, R.id.share_weixin, R.id.share_qq, R.id.share_pengyouquan, R.id.share_weibo, R.id.share_kongjian})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.submit_num:
                //提交介绍人号码

                break;
            case R.id.share_weixin:
                break;
            case R.id.share_qq:
                break;
            case R.id.share_pengyouquan:
                break;
            case R.id.share_weibo:
                break;
            case R.id.share_kongjian:
                break;
        }
    }
}
