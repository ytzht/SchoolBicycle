package com.school.bicycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.Mycoupon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Mycoupon_adapter extends BaseAdapter {

    private Context context;
    private List<Mycoupon.BodyBean> data;


    public Mycoupon_adapter(Context context, List<Mycoupon.BodyBean> data) {
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
            view = myInflater.inflate(R.layout.mycoupon_adapter, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }


        viewHolde.timeCoupon.setText(data.get(i).getStart_time() + "-" + data.get(i).getEnd_time());
        if (data.get(i).getCou_type().equals("折扣")) {
            viewHolde.rmb.setText("");
            viewHolde.manyong.setText("所有可用");
            viewHolde.dikou.setText(data.get(i).getCou_discount() * 10 + "折");
            viewHolde.zhekouleixing.setText("折扣券");
            viewHolde.couponColor.setImageResource(R.drawable.ico_coupon_orange);
        } else if (data.get(i).getCou_type().equals("满减")) {
            viewHolde.dikou.setText(data.get(i).getCou_cut() + "");
            viewHolde.manyong.setText("满" + data.get(i).getCou_full() + "元可用");
            if (data.get(i).getCou_usedin().equals("时租")) {
                viewHolde.couponColor.setImageResource(R.drawable.ico_coupon_blue);
                viewHolde.zhekouleixing.setText("时租券");
            } else {
                viewHolde.couponColor.setImageResource(R.drawable.ico_coupon_green);
                viewHolde.zhekouleixing.setText("日租券");
            }

        }

        return view;
    }



    static class ViewHolder {
        @BindView(R.id.coupon_color)
        ImageView couponColor;
        @BindView(R.id.dikou)
        TextView dikou;
        @BindView(R.id.manyong)
        TextView manyong;
        @BindView(R.id.time_coupon)
        TextView timeCoupon;
        @BindView(R.id.rmb)
        TextView rmb;
        @BindView(R.id.zhekouleixing)
        TextView zhekouleixing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
