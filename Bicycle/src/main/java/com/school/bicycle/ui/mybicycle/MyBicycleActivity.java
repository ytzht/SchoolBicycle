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
import com.school.bicycle.entity.SharedBikeList;
import com.school.bicycle.global.Apis;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.global.L;
import com.school.bicycle.global.UserService;
import com.school.bicycle.ui.MyRoute.MyRoute_Activity;
import com.school.bicycle.ui.Myeserve.MyReserveActivity;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.MySelectorDecorators;
import com.school.bicycle.utils.OneDayDecorator;
import com.school.bicycle.utils.SelectDecorator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    Mybiycle m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bicycle);
        ButterKnife.bind(this);
        setToolbarText("我的车辆");
        cookie = new UserService(MyBicycleActivity.this).getCookie();
        initview();

    }

    private void initview() {

        String url = Apis.Base +
                Apis.myBike;
        String cookie = new UserService(MyBicycleActivity.this).getCookie();

        OkHttpUtils.get()
                .url(url)
                .addHeader("cookie", cookie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response", response);
                        m = gson.fromJson(response, Mybiycle.class);
                        if (m.getCode() == 1) {
                            bikeNumber.setText(m.getBike_number());
                            shareIncome.setText(m.getShare_income());
                        } else {
                            bikeNumber.setText("无");
                            shareIncome.setText(m.getShare_income());
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


                String url = Apis.Base + Apis.dayLeaseLists;
                format = new SimpleDateFormat("yyyy-MM-dd");
                OkHttpUtils
                        .post()
                        .url(url)
                        .addHeader("cookie", cookie)
//                .addParams("bike_number", bike_number)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                L.d(response);
                                SharedBikeList d = gson.fromJson(response, SharedBikeList.class);
                                if (d.getCode() == 1) {
                                    unList.clear();
                                    canList.clear();
                                    showAlert(d.getBody());

                                } else {
                                    showShort(d.getMsg());
                                }

                            }
                        });


                break;
            case R.id.re_mypolice_mybiycle:
                //报警
                break;
        }
    }


    private static final String TAG = "=====";
    private MaterialCalendarView myCalendar;
    private TextView tv_ok, tv_cancel;

    List<Date> unList = new ArrayList<>();
    List<SharedBikeList.BodyBean> canList = new ArrayList<>();
    private CheckBox cal_cb;

    private void showAlert(List<SharedBikeList.BodyBean> list) {

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.mybiycle_calendar, null, false);
        dialog = new AlertDialog.Builder(MyBicycleActivity.this).setView(view).setCancelable(false).show();
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cal_cb = (CheckBox) view.findViewById(R.id.cal_cb);
        dialog.setCancelable(true);
        myCalendar = (MaterialCalendarView) view.findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历

        if (list != null) {
            for (int j = 0; j < list.size(); j++) {
                try {
                    Date date = format.parse(list.get(j).getStart_time());
                    if (list.get(j).getLease_status() == 0) {
                        canList.add(list.get(j));
                        myCalendar.setDateSelected(date, true);
                    } else {
                        unList.add(date);
                        myCalendar.addDecorators(new MySelectorDecorators(MyBicycleActivity.this),
                                new SelectDecorator(MyBicycleActivity.this, date));
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        setClickCalendar();

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
                        cookie = new UserService(MyBicycleActivity.this).getCookie();
                        Map<String, String> map = new HashMap<>();
                        map.put("share_date", s);
                        OkHttpUtils.post()
                                .params(map)
                                .addHeader("cookie", cookie)
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

    private void setClickCalendar() {
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, final CalendarDay date, boolean selected) {
                if (selected) {
                    myCalendar.setDateSelected(date, true);
                    if (unList.size() > 0) {
                        for (int i = 0; i < unList.size(); i++) {
                            if (unList.get(i).getTime() == date.getDate().getTime()) {
                                myCalendar.setDateSelected(date, false);
                                showShort("不可取消");
                            }
                        }
                    }
                } else {
                    int q = 0;

                    if (canList.size() > 0) {


                        for (int i = 0; i < canList.size(); i++) {

                            try {
                                if (format.parse(canList.get(i).getStart_time()).getTime() == date.getDate().getTime()) {
                                    q = 1;
                                    OkHttpUtils.post()
                                            .url(Apis.Base + Apis.CancelShareMyBike)
                                            .addParams("sids", canList.get(i).getSid()+"")
                                            .addHeader("cookie", cookie).build().execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            BaseResult result = gson.fromJson(response, BaseResult.class);
                                            if (result.getCode() == 1){
                                                myCalendar.setDateSelected(date, false);
                                                showShort(result.getMsg());
                                            }else {
                                                showShort(result.getMsg());
                                            }
                                        }
                                    });

                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    if (q == 0)
                        myCalendar.setDateSelected(date, false);
                }

            }
        });
    }

    String cookie;
    DateFormat format;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天

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
                .setMaximumDate(CalendarDay.from(today.getYear(), today.getMonth() + 1, today.getDay()))
                .commit();
        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
            }
        });

        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);
    }
}
