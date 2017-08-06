package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.school.bicycle.R;
import com.school.bicycle.adapter.Mycoupon_chooles_adapter;
import com.school.bicycle.adapter.Mycoupon_choolesday_adapter;
import com.school.bicycle.entity.DayLeaseOrder;
import com.school.bicycle.entity.FindNotPayRoute;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.pay.PayActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Mycoupon_choolesday_Activity extends BaseToolBarActivity {



    @BindView(R.id.mycoupon_list)
    ListView mycouponList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoupon_chooles);
        ButterKnife.bind(this);
        initview();
        setToolbarText("卡券选择");

    }

    private void initview() {
        final DayLeaseOrder dayLeaseOrder = (DayLeaseOrder) getIntent().getSerializableExtra("dayLeaseOrder");
        Mycoupon_choolesday_adapter mycoupon_choolesday_adapter = new Mycoupon_choolesday_adapter(getBaseContext(),dayLeaseOrder.getCoupon() );
        mycouponList.setAdapter(mycoupon_choolesday_adapter);
        mycouponList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("cid的",dayLeaseOrder.getCoupon().get(position).getCou_number()+"");
                PayActivity.cid = dayLeaseOrder.getCoupon().get(position).getUsercou_id();
                finish();
            }
        });
    }


}
