package com.school.bicycle.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.OneDayDecorator;

import java.util.List;

public class CalendarSelectActivity extends BaseToolBarActivity {

    private static final String TAG = "SignHistoryAct=====";
    private MaterialCalendarView myCalendar;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
    private TextView tv_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_select);

        tv_ok = (TextView) findViewById(R.id.tv_ok);
        myCalendar = (MaterialCalendarView) findViewById(R.id.calendar_md);
        myCalendarInit();//初始化日历


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<CalendarDay> selectedDates = myCalendar.getSelectedDates();

                if (selectedDates.size()>0) {
                    for (int i = 0; i < selectedDates.size(); i++) {
                        Log.d(TAG, "onClick: "+selectedDates.get(i).getDate().getTime());
                    }

                }else {
                    showShort("请选择日期");
                }
            }
        });
    }

    public void myCalendarInit() {
        myCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        myCalendar.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large);
        myCalendar.setDateTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        myCalendar.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        myCalendar.setSelected(false);
        myCalendar.setEnabled(false);
        myCalendar.setClickable(false);
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
                if (selected) {
                    myCalendar.setDateSelected(date, true);
                }else {
                    myCalendar.setDateSelected(date, false);
                }
//                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1, list);

            }
        });

        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);

//        signDataInit(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1);
    }
}
