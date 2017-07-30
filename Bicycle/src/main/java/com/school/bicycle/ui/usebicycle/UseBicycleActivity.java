package com.school.bicycle.ui.usebicycle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.adapter.QuerytBikeListByDate_adapter;
import com.school.bicycle.entity.QueryBikeListByBikeNumber;
import com.school.bicycle.entity.QueryBikeListByDate;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.authentication.RealnameActivity;
import com.school.bicycle.ui.main.MainActivity;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.OneDayDecorator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UseBicycleActivity extends BaseToolBarActivity {

    @BindView(R.id.tv_bicyclenum_use)
    TextView tvBicyclenumUse;
    @BindView(R.id.tv_date_use)
    TextView tvDateUse;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_search_bicyclenum_num)
    EditText llSearchBicyclenumNum;
    @BindView(R.id.ll_search_bicyclenum_sure)
    TextView llSearchBicyclenumSure;
    @BindView(R.id.ll_search_bicyclenum)
    LinearLayout llSearchBicyclenum;
    @BindView(R.id.lv_show_usebicycle)
    ListView lvShowUsebicycle;
    @BindView(R.id.usebiycle_bynum_biyclenum)
    TextView usebiycleBynumBiyclenum;
    @BindView(R.id.usebiycle_bynum_biycleaddress)
    TextView usebiycleBynumBiycleaddress;
    @BindView(R.id.usebiycle_bynum_biycletime)
    TextView usebiycleBynumBiycletime;
    @BindView(R.id.usebiycle_bynum)
    LinearLayout usebiycleBynum;
    @BindView(R.id.iv_usebiycle_bynum_biycleaddress)
    ImageView ivUsebiycleBynumBiycleaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_bicycle);
        ButterKnife.bind(this);
        setToolbarText("用车列表");
        initonclick();
        usebiycleBynum.setVisibility(View.GONE);
        llSearchBicyclenum.setVisibility(View.GONE);
        llSearchBicyclenumNum.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //软键盘搜索
        llSearchBicyclenumNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {

                if (i == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String url = getResources().getString(R.string.baseurl) + "order/queryBikeListByBikeNumber";

                    String cookie = new UserService(UseBicycleActivity.this).getCookie();

                    OkHttpUtils
                            .post()
                            .url(url)
                            .addHeader("cookie",cookie)
                            .addParams("bike_number", llSearchBicyclenumNum.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("response", response);
                                    QueryBikeListByBikeNumber queryBikeListByBikeNumber = gson.fromJson(response, QueryBikeListByBikeNumber.class);
                                    if (queryBikeListByBikeNumber.getCode() == 0) {
                                        showShort(queryBikeListByBikeNumber.getMsg());
                                    } else {
                                        usebiycleBynum.setVisibility(View.VISIBLE);
                                        lvShowUsebicycle.setVisibility(View.GONE);
                                        usebiycleBynumBiyclenum.setText("车牌号：" + queryBikeListByBikeNumber.getBike_info().getNumber());
                                        usebiycleBynumBiycleaddress.setText(queryBikeListByBikeNumber.getBike_info().getAddress());

                                        if (queryBikeListByBikeNumber.getBike_info().getColor().equals("yellow")) {
                                            ivUsebiycleBynumBiycleaddress.setImageResource(R.drawable.ico_bicycle_yellow);
                                            usebiycleBynumBiycletime.setText("共享时间：" + queryBikeListByBikeNumber.getBike_info().getValid_time());
                                        } else if (queryBikeListByBikeNumber.getBike_info().getColor().equals("green")) {
                                            ivUsebiycleBynumBiycleaddress.setImageResource(R.drawable.ico_bicycle_green);
                                            usebiycleBynumBiycletime.setText("随时可用");
                                        }
                                    }

                                }
                            });
                    return true;
                }
                return false;
            }
        });

    }

    private void initonclick() {
        lvShowUsebicycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(UseBicycleActivity.this, MainActivity.class);
                it.putExtra("onebikeinfo",queryBikeListByDate.getBike_info().get(position));
                startActivity(it);
            }
        });
    }

    private AlertDialog dialog;

    @OnClick({R.id.tv_bicyclenum_use, R.id.tv_date_use, R.id.ll_search_bicyclenum_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bicyclenum_use:
                //按车牌号搜索
                llSearch.setVisibility(View.GONE);
                llSearchBicyclenum.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_date_use:
                //按日期搜索
                showAlert();

                break;

            case R.id.ll_search_bicyclenum_sure:
                llSearch.setVisibility(View.VISIBLE);
                llSearchBicyclenum.setVisibility(View.GONE);
                break;
        }
    }

    private static final String TAG = "=====";
    private MaterialCalendarView myCalendar;
    private TextView tv_ok;
    QueryBikeListByDate queryBikeListByDate;

    private void showAlert() {

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.usebiycle_calendar, null, false);
        dialog = new AlertDialog.Builder(UseBicycleActivity.this).setView(view).setCancelable(false).show();
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<CalendarDay> selectedDates = myCalendar.getSelectedDates();

                String s = "";
                if (selectedDates.size() > 0) {
                    for (int i = 0; i < selectedDates.size(); i++) {
                        String format = new SimpleDateFormat("yyyy-MM-dd").format(selectedDates.get(i).getDate());
                        Log.d(TAG, "onClick: " + format);
                        if (selectedDates.size() == i + 1) {
                            s = s + format;
                        } else {
                            s = s + format + ",";
                        }
                    }

                    Log.d(TAG, s);
                    String url = Apis.Base + Apis.queryBikeListByDate;
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("dates", s)
                            .addParams("pageNumber", 1 + "")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    usebiycleBynum.setVisibility(View.GONE);
                                    lvShowUsebicycle.setVisibility(View.VISIBLE);
                                    Log.d("response", response);
                                    queryBikeListByDate = gson.fromJson(response, QueryBikeListByDate.class);
                                    if (queryBikeListByDate.getCode() == 0) {
                                        showShort(queryBikeListByDate.getMsg());
                                    } else {
                                        QuerytBikeListByDate_adapter querytBikeListByDate_adapter = new QuerytBikeListByDate_adapter(getBaseContext(), queryBikeListByDate.getBike_info());
                                        lvShowUsebicycle.setAdapter(querytBikeListByDate_adapter);
                                        dialog.dismiss();
                                    }
                                }
                            });
                } else {
                    showShort("请选择日期");
                }
            }
        });


    }


    public void myCalendarInit() {
        myCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        myCalendar.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setDateTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Small);
        myCalendar.setSelected(false);
        myCalendar.setEnabled(false);
        myCalendar.setClickable(false);
        CalendarDay today = CalendarDay.today();
        myCalendar.state().edit()
                .setMinimumDate(CalendarDay.today())
                .setMaximumDate(CalendarDay.from(today.getYear(), today.getMonth() + 2, today.getDay()))
                .commit();
        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
//        init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);
        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//                signDataInit(date.getYear(), date.get月租() + 1);
            }
        });
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

                if (widget.getSelectedDates().size() > 2) {

                    new AlertDialog.Builder(UseBicycleActivity.this).setTitle("提示").setMessage("天数选择超限")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                    myCalendar.setDateSelected(date, false);

                } else {

                    if (selected) {
                        myCalendar.setDateSelected(date, true);
                    } else {
                        myCalendar.setDateSelected(date, false);
                    }
                }
//                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);

            }
        });
        OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);

//        signDataInit(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1);
    }
}
