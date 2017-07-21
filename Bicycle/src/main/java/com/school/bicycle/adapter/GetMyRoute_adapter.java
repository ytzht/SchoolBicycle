package com.school.bicycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.GetMyRoute;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class GetMyRoute_adapter extends BaseAdapter {

    private Context context;
    private List<GetMyRoute.BodyBean> data;


    public GetMyRoute_adapter(Context context, List<GetMyRoute.BodyBean> data) {
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
            view = myInflater.inflate(R.layout.getmyroute_layout, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }

        viewHolde.OrdernumberMyroute.setText("订单号："+data.get(i).getRid()+"");
        viewHolde.carnumberMyroute.setText("车牌号："+data.get(i).getBike_number());
        viewHolde.timeMyroute.setText("下单时间："+data.get(i).getStart_time());

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.Ordernumber_myroute)
        TextView OrdernumberMyroute;
        @BindView(R.id.carnumber_myroute)
        TextView carnumberMyroute;
        @BindView(R.id.time_myroute)
        TextView timeMyroute;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
