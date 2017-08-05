package com.school.bicycle.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.school.bicycle.R;
import com.school.bicycle.adapter.Mycoupon_chooles_adapter;
import com.school.bicycle.entity.FindNotPayRoute;
import com.school.bicycle.global.BaseToolBarActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Mycoupon_chooles_Activity extends BaseToolBarActivity {



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
        final FindNotPayRoute findNotPayRoute = (FindNotPayRoute) getIntent().getSerializableExtra("findNotPayRoute");
        Mycoupon_chooles_adapter mycoupon_chooles_adapter = new Mycoupon_chooles_adapter(getBaseContext(),findNotPayRoute.getCoupon() );
        mycouponList.setAdapter(mycoupon_chooles_adapter);
        mycouponList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("cid的",findNotPayRoute.getCoupon().get(position).getCou_number()+"");
                OverPayActivity.cid = findNotPayRoute.getCoupon().get(position).getCou_number()+"";
                OverPayActivity.cidprice = findNotPayRoute.getCoupon().get(position).getCou_discount()+"";
                finish();
            }
        });
    }


}
