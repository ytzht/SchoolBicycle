package com.school.bicycle.ui.mybicycle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.entity.BaseResult;
import com.school.bicycle.entity.Mybiycle;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.ui.MyRoute.MyRoute_Activity;
import com.school.bicycle.ui.Myeserve.MyReserveActivity;
import com.school.bicycle.utils.MySelectorDecorators;
import com.school.bicycle.utils.SelectDecorator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MyBicycleActivity extends BaseToolBarActivity {

    @BindView(R.id.re_biyclenum_mybiycle)
    RelativeLayout reBiyclenumMybiycle;
    @BindView(R.id.re_Income_mybiycle)
    RelativeLayout reIncomeMybiycle;
    @BindView(R.id.re_myTrip_mybiycle)
    RelativeLayout reMyTripMybiycle;
    @BindView(R.id.re_mystate_mybiycle)
    RelativeLayout reMystateMybiycle;
    @BindView(R.id.re_myReservation_mybiycle)
    RelativeLayout reMyReservationMybiycle;
    @BindView(R.id.re_mypolice_mybiycle)
    RelativeLayout reMypoliceMybiycle;
    @BindView(R.id.bike_number)
    TextView bikeNumber;
    @BindView(R.id.share_income)
    TextView shareIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bicycle);
        ButterKnife.bind(this);
        setToolbarText("我的车辆");
        initview();
    }

    private void initview() {

        String url = Apis.Base +
                Apis.myBike;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        Mybiycle m = gson.fromJson(response, Mybiycle.class);
                        if (m.getCode() == 1) {
                            bikeNumber.setText(m.getBike_number());
                            shareIncome.setText(m.getShare_income());
                        } else {
                            showShort(m.getMsg());
                        }
                    }
                });

    }

    private AlertDialog dialog;


    @OnClick({R.id.re_biyclenum_mybiycle, R.id.re_Income_mybiycle, R.id.re_myTrip_mybiycle, R.id.re_mystate_mybiycle, R.id.re_myReservation_mybiycle, R.id.re_mypolice_mybiycle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.re_biyclenum_mybiycle:
                //车辆号
                break;
            case R.id.re_Income_mybiycle:
                //共享收入
                break;
            case R.id.re_myTrip_mybiycle:
                //我的行程
                startActivity(MyRoute_Activity.class);
                break;
            case R.id.re_mystate_mybiycle:
                startActivity(MyReserveActivity.class);
                //我的预定
                break;
            case R.id.re_myReservation_mybiycle:
                //共享设置
                showAlert();
                break;
            case R.id.re_mypolice_mybiycle:
                //报警
                break;
        }
    }


    private static final String TAG = "=====";
    private MaterialCalendarView myCalendar;
    private TextView tv_ok, tv_cancel;

    private CheckBox cal_cb;

    private void showAlert() {

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.mybiycle_calendar, null, false);
        dialog = new AlertDialog.Builder(MyBicycleActivity.this).setView(view).setCancelable(false).show();
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cal_cb = (CheckBox) view.findViewById(R.id.cal_cb);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cal_cb.isChecked()) {
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
                        // TODO: 2017/7/18 接口25

                        Map<String, String> map = new HashMap<>();
                        map.put("share_date", s);
                        OkHttpUtils.post()
                                .params(map)
                                .url(Apis.Base + Apis.StartShareMyBike)
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d(TAG, "onResponse: " + response);
                                BaseResult result = (new Gson()).fromJson(response, BaseResult.class);
                                if (result.getCode() == 1) {
                                    showShort(result.getMsg());
                                    if (dialog.isShowing()) dialog.dismiss();
                                } else {
                                    showShort(result.getMsg());
                                }
                            }
                        });
                    } else {
                        showShort("请选择日期");
                    }
                } else {
                    new AlertDialog.Builder(MyBicycleActivity.this).setTitle("提示").setMessage("请仔细阅读共享协议，如果同意请勾选以继续")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }


            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) dialog.dismiss();
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
//        init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1, list);
        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//                signDataInit(date.getYear(), date.getMonth() + 1);
            }
        });
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

//                if (widget.getSelectedDates().size() > 2) {
//
//                    myCalendar.setDateSelected(date, false);
//
//                } else {

                if (selected) {
                    myCalendar.setDateSelected(date, true);
                } else {
                    myCalendar.setDateSelected(date, false);
                }
//                }
//                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1, list);

            }
        });
//        MySelectorDecorator decorator = new MySelectorDecorator(this);
//        myCalendar.addDecorators(decorator);


        Date date = null;
        Date date1 = null;
        String str = "2017-7-18";
        String str1 = "2017-7-11";
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date1 = format1.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            date = format1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        myCalendar.addDecorators(new MySelectorDecorators(this), new SelectDecorator(this, date));
        myCalendar.addDecorators(new MySelectorDecorators(this), new SelectDecorator(this, date1));

//        myCalendar.setDateSelected(date1, true);
//        signDataInit(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1);
    }


}
