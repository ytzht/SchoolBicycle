package com.school.bicycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.Usebicycle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Usebicycle_adapter extends BaseAdapter {

    private Context context;
    private List<Usebicycle> data;


    public Usebicycle_adapter(Context context, List<Usebicycle> data) {
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
