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

        String str = data.get(i).getCreate_time().substring(2, 10);
        if (data.get(i).getRoute_type() == 0) {
            viewHolde.OrdernumberMyroute.setText("骑行时间：" + str + "    骑行花费：" + data.get(i).getTotal_fee() + "元");
        } else if (data.get(i).getRoute_type() == 1) {
            viewHolde.OrdernumberMyroute.setText("骑行时间：" + str + "   骑行花费：" + data.get(i).getTotal_fee() + "元");
        } else if (data.get(i).getRoute_type() == 2) {
            viewHolde.OrdernumberMyroute.setText("骑行时间：" + data.get(i).getTime_span() + "分钟    骑行花费：" + data.get(i).getTotal_fee() + "元");
        }

        viewHolde.carnumberMyroute.setText("车牌号：" + data.get(i).getBike_number());
        viewHolde.timeMyroute.setText(data.get(i).getCreate_time());
        if (i + 1 == 1) {
            viewHolde.shang.setVisibility(View.GONE);
        } else if (i + 1 > 1 && i + 1 < data.size()) {
            viewHolde.xia.setVisibility(View.VISIBLE);
            viewHolde.shang.setVisibility(View.VISIBLE);
        } else if (i + 1 == data.size()) {
            viewHolde.xia.setVisibility(View.GONE);
        }
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.Ordernumber_myroute)
        TextView OrdernumberMyroute;
        @BindView(R.id.carnumber_myroute)
        TextView carnumberMyroute;
        @BindView(R.id.time_myroute)
        TextView timeMyroute;
        @BindView(R.id.shang)
        View shang;
        @BindView(R.id.xia)
        View xia;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
