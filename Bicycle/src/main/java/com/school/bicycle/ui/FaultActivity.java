package com.school.bicycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.FailureList;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FaultActivity extends BaseToolBarActivity {

    private static final int REQUEST_CODE = 200;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.rlv_problem)
    RecyclerView rlvProblem;

    private FaultAdapter adapter;
    private List<FailureList.ProblemsBean> problems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault);
        ButterKnife.bind(this);
        setToolbarText("故障申报");

        adapter = new FaultAdapter();
        rlvProblem.setLayoutManager(new LinearLayoutManager(this));
        rlvProblem.setAdapter(adapter);

        String cookie = new UserService(FaultActivity.this).getCookie();

        OkHttpUtils.get()
                .url(Apis.Base + Apis.FailureList) .addHeader("cookie",cookie)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                FailureList list = (new Gson()).fromJson(response, FailureList.class);
                if (list.getCode() == 1) {
                    problems = list.getProblems();

                    adapter.notifyDataSetChanged();


                } else {
                    showShort(list.getMsg());
                }


            }
        });


    }

    @OnClick({R.id.iv_scan, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent intent = new Intent(FaultActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btn_submit:

                String s = "";
                for (int i = 0; i < problems.size(); i++) {
                    if (problems.get(i).isClick()) {
                        s = s + problems.get(i).getProblem() + ",";
                    }

                }

                if (s.equals("")) {
                    showShort("请选择出现的问题");
                    return;
                }

                if (etInput.getText().toString().equals("")) {
                    showShort("请输入车辆编号");
                    return;
                }
                Map<String, String> map = new HashMap<>();

                map.put("bike_number", etInput.getText().toString());
                map.put("problem", s);
                L.d(s);
                OkHttpUtils.post()
                        .params(map)
                        .url(Apis.Base + Apis.FailureReporting)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        BaseResult result = (new Gson()).fromJson(response, BaseResult.class);
                        if (result.getCode() == 1) {
                            showShort(result.getMsg());
                            finish();
                        } else {
                            showShort(result.getMsg());
                        }

                    }
                });


                break;
        }
    }

    class FaultAdapter extends RecyclerView.Adapter<FaultAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(FaultActivity.this).inflate(R.layout.item_fault, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.tvTxt.setText(problems.get(position).getProblem());
            holder.cbFault.setSelected(false);

            holder.cbFault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    problems.get(position).setClick(isChecked);
                }
            });
        }

        @Override
        public int getItemCount() {
            return problems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTxt;
            CheckBox cbFault;

            public ViewHolder(View itemView) {
                super(itemView);
                tvTxt = (TextView) itemView.findViewById(R.id.tv_txt);
                cbFault = (CheckBox) itemView.findViewById(R.id.cb_fault);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    etInput.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(FaultActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
