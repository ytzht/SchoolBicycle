package com.school.bicycle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.QueryShareDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ShareDetails_adapter extends BaseAdapter {

    private Context context;
    private List<QueryShareDetails.ShareDetailsBean> data;


    public ShareDetails_adapter(Context context, List<QueryShareDetails.ShareDetailsBean> data) {
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
        Log.d("data", data.get(i).getOrder_no());
        if (view == null) {
            view = myInflater.inflate(R.layout.consumption_layout, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }

        viewHolde.detailNum.setText(data.get(i).getLease_type());
        viewHolde.detailType.setText(data.get(i).getLease_type() + ":" + data.get(i).getShare_income() + "元");
        viewHolde.detailMoney.setText("下单时间：" + data.get(i).getCreate_time());
        viewHolde.detailPaytype.setVisibility(View.GONE);
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
