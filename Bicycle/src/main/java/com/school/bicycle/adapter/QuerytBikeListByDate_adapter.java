package com.school.bicycle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.QueryBikeListByBikeNumber;
import com.school.bicycle.entity.QueryBikeListByDate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class QuerytBikeListByDate_adapter extends BaseAdapter {

    private Context context;
    private List<QueryBikeListByDate.BikeInfoBean> data;


    public QuerytBikeListByDate_adapter(Context context, List<QueryBikeListByDate.BikeInfoBean> data) {
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

        if (view == null) {
            view = myInflater.inflate(R.layout.usebiycle_adapter, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }
        viewHolde.tvBiyclenumAda.setText("车牌号："+ data.get(i).getNumber());
        viewHolde.tvDistanceAda.setText(data.get(i).getAddress());
        if (data.get(i).getColor().equals("yellow")){
            viewHolde.ivBicycleAda.setImageResource(R.drawable.ico_bicycle_yellow);
            viewHolde.tvSharingtimeAda.setText("共享时间："+data.get(i).getValid_time());
        }else if (data.get(i).getColor().equals("green")){
            viewHolde.ivBicycleAda.setImageResource(R.drawable.ico_bicycle_green);
            viewHolde.tvSharingtimeAda.setText("随时可用");
        }else if (data.get(i).getColor().equals("red")){
            viewHolde.ivBicycleAda.setImageResource(R.drawable.ico_bicycle_red);
            viewHolde.tvSharingtimeAda.setText("已被长租");
        }else if (data.get(i).getColor().equals("blue")){
            viewHolde.ivBicycleAda.setImageResource(R.drawable.ico_bicycle_blue);
            viewHolde.tvSharingtimeAda.setText("这是您的长租车辆，随时扫码使用");
        }

        return view;
    }



    static class ViewHolder {
        @BindView(R.id.tv_biyclenum_ada)
        TextView tvBiyclenumAda;
        @BindView(R.id.tv_Distance_ada)
        TextView tvDistanceAda;
        @BindView(R.id.iv_bicycle_ada)
        ImageView ivBicycleAda;
        @BindView(R.id.tv_Sharingtime_ada)
        TextView tvSharingtimeAda;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
