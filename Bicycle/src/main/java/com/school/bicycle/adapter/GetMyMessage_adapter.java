package com.school.bicycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.school.bicycle.R;
import com.school.bicycle.entity.GetMyMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */

public class GetMyMessage_adapter extends BaseAdapter {

    private Context context;
    private List<GetMyMessage.MessageBean> data;


    public GetMyMessage_adapter(Context context, List<GetMyMessage.MessageBean> data) {
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
            view = myInflater.inflate(R.layout.getmymessage_layout, null);
            viewHolde = new ViewHolder(view);
            view.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) view.getTag();
        }

        viewHolde.mymessageTitle.setText( data.get(i).getTitle());
        viewHolde.mymessageContent.setText(data.get(i).getContent());

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.mymessage_title)
        TextView mymessageTitle;
        @BindView(R.id.mymessage_content)
        TextView mymessageContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
