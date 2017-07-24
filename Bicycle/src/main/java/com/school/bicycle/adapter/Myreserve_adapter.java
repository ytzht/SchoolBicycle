package com.school.bicycle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.MyAppoint;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Myreserve_adapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<MyAppoint.MyAppointBean> data;


    private static final String TAG = "Myreserve_adapter";
    private Callback mCallback;

    public Myreserve_adapter(Context context, List<MyAppoint.MyAppointBean> data, Callback callback) {
        this.context = context;
        this.data = data;
        this.mCallback = callback;
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
            view = myInflater.inflate(R.layout.myreserve_adapter, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }

        viewHolde.MyAppointNum.setText(data.get(i).getNumber());
        viewHolde.MyAppointTime.setText(data.get(i).getAppoint_time());
        viewHolde.MyAppointDetial.setOnClickListener(this);
        viewHolde.MyAppointCancle.setOnClickListener(this);
        viewHolde.MyAppointDetial.setTag(i);
        viewHolde.MyAppointCancle.setTag(i);
        String color = data.get(i).getNumber().substring(0, 1);
        if (color.equals("12")) {
            viewHolde.MyAppointCarcolor.setImageResource(R.drawable.ico_doublebicycle_yellow);
        } else {
            viewHolde.MyAppointCarcolor.setImageResource(R.drawable.ico_bicycle_yellow);
        }
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.MyAppoint_carcolor)
        ImageView MyAppointCarcolor;
        @BindView(R.id.MyAppoint_num)
        TextView MyAppointNum;
        @BindView(R.id.MyAppoint_detial)
        TextView MyAppointDetial;
        @BindView(R.id.MyAppoint_time)
        TextView MyAppointTime;
        @BindView(R.id.MyAppoint_cancle)
        TextView MyAppointCancle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private Animation animation;
    private int click_id;

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.MyAppoint_detial:
                mCallback.ondetialClick(v);
                break;
            case R.id.MyAppoint_cancle:
                mCallback.oncancleClick(v);
                break;
            default:
                break;
        }
        Log.d(TAG, "onClick");
    }

    public interface Callback {
        public void ondetialClick(View v);

        public void oncancleClick(View v);

    }
}
