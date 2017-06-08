package com.school.bicycle.utils;//package com.onekeyask.lawyer.utils;
//
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//
//import com.onekeyask.lawyer.R;
//
//import java.util.List;
//
///**
// * Created by ytzht on 2017/5/5.am
// */
//
//public class SpinerPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {
//
//    private Context mContext;
//    private ListView mListView;
//    private NormalSpinerAdapter mAdapter;
//    private IOnItemSelectListener mItemSelectListener;
//
//
//    public SpinerPopWindow(Context context)
//    {
//        super(context);
//
//        mContext = context;
//        init();
//    }
//
//
//    public void setItemListener(IOnItemSelectListener listener){
//        mItemSelectListener = listener;
//    }
//
//
//    private void init()
//    {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.spiner_window_layout, null);
//        setContentView(view);
//        setWidth(LayoutParams.WRAP_CONTENT);
//        setHeight(LayoutParams.WRAP_CONTENT);
//
//        setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0x00);
//        setBackgroundDrawable(dw);
//
//
//        mListView = (ListView) view.findViewById(R.id.listview);
//
//
//        mAdapter = new NormalSpinerAdapter(mContext);
//        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(this);
//    }
//
//
//    public void refreshData(List<String> list, int selIndex)
//    {
//        if (list != null && selIndex  != -1)
//        {
//            mAdapter.refreshData(list, selIndex);
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
//        dismiss();
//        if (mItemSelectListener != null){
//            mItemSelectListener.onItemClick(pos);
//        }
//    }
//
//
//
//
//}
