package com.school.bicycle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.school.bicycle.R;
import com.school.bicycle.entity.Consumption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Consumption_adapter extends BaseAdapter {

    private Context context;
    private List<Consumption.ConsumerDetailsBean> data;


    public Consumption_adapter(Context context, List<Consumption.ConsumerDetailsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolde = null;
        LayoutInflater myInflater = LayoutInflater.from(context);
        Log.d("data",data.get(i).getOrder_no());
        if (view == null) {
            view = myInflater.inflate(R.layout.consumption_layout, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }

        viewHolde.detailNum.setText(data.get(i).getOrder_status());
        viewHolde.detailType.setText(data.get(i).getOrder_status()+":"+data.get(i).getTotal_fee()+"元");
        viewHolde.detailMoney.setText(data.get(i).getCreate_time());
        if (null==data.get(i).getPay_type()){
            viewHolde.detailPaytype.setText("");
        }else if(data.get(i).getPay_type().equals("zfb")){
            viewHolde.detailPaytype.setText("支付宝");
        }else if (data.get(i).getPay_type().equals("wx")){
            viewHolde.detailPaytype.setText("微信");
        }

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.detail_num)
        TextView detailNum;
        @BindView(R.id.detail_type)
        TextView detailType;
        @BindView(R.id.detail_money)
        TextView detailMoney;
        @BindView(R.id.detail_paytype)
        TextView detailPaytype;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
