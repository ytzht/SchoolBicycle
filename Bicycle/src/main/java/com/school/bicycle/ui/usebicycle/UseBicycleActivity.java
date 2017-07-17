package com.school.bicycle.ui.usebicycle;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.Usebicycle_adapter;
import com.school.bicycle.entity.QueryBikeListByBikeNumber;
import com.school.bicycle.global.BaseToolBarActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UseBicycleActivity extends BaseToolBarActivity {

    @BindView(R.id.tv_bicyclenum_use)
    TextView tvBicyclenumUse;
    @BindView(R.id.tv_date_use)
    TextView tvDateUse;
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
        llSearchBicyclenumNum.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //软键盘搜索
        llSearchBicyclenumNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {

                if (i == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String url = getResources().getString(R.string.baseurl) + "order/queryBikeListByBikeNumber";
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("bike_number", "011130")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    QueryBikeListByBikeNumber queryBikeListByBikeNumber = gson.fromJson(response, QueryBikeListByBikeNumber.class);
                                    if (queryBikeListByBikeNumber.getCode() == 0) {
                                        showLong(queryBikeListByBikeNumber.getMsg());
                                    } else {
                                        Usebicycle_adapter usebicycle_adapter = new Usebicycle_adapter(getBaseContext(), queryBikeListByBikeNumber.getBike_info());
                                        lvShowUsebicycle.setAdapter(usebicycle_adapter);
                                    }

                                }
                            });
                    return true;
                }
                return false;
            }
        });

    }

    @OnClick({R.id.tv_bicyclenum_use, R.id.tv_date_use, R.id.ll_search_bicyclenum_sure})
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

            case R.id.ll_search_bicyclenum_sure:
                llSearch.setVisibility(View.VISIBLE);
                llSearchBicyclenum.setVisibility(View.GONE);
                break;
        }
    }
}
