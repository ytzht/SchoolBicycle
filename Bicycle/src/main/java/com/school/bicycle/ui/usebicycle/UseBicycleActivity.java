package com.school.bicycle.ui.usebicycle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseBicycleActivity extends BaseToolBarActivity {

    @BindView(R.id.tv_bicyclenum_use)
    TextView tvBicyclenumUse;
    @BindView(R.id.tv_date_use)
    TextView tvDateUse;
    @BindView(R.id.tv_time_use)
    TextView tvTimeUse;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_search_bicyclenum_num)
    EditText llSearchBicyclenumNum;
    @BindView(R.id.ll_search_bicyclenum_sure)
    TextView llSearchBicyclenumSure;
    @BindView(R.id.ll_search_bicyclenum)
    LinearLayout llSearchBicyclenum;
    @BindView(R.id.lv_show_usebicycle)
    ListView lvShowUsebicycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_bicycle);
        ButterKnife.bind(this);
        setToolbarText("用车列表");
        llSearchBicyclenum.setVisibility(View.GONE);


    }

    @OnClick({R.id.tv_bicyclenum_use, R.id.tv_date_use, R.id.tv_time_use, R.id.ll_search_bicyclenum_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bicyclenum_use:
                //按车牌号搜索
                llSearch.setVisibility(View.GONE);
                llSearchBicyclenum.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_date_use:
                //按日期搜索
                break;
            case R.id.tv_time_use:
                //按时间搜索
                break;
            case R.id.ll_search_bicyclenum_sure:
                if (llSearchBicyclenumSure.getText().equals("取消")){
                    llSearch.setVisibility(View.VISIBLE);
                    llSearchBicyclenum.setVisibility(View.GONE);
                }else {

                }
                break;
        }
    }
}
