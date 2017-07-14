package com.school.bicycle.ui.search;

import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;
import com.school.bicycle.utils.HighlightWeekendsDecorator;
import com.school.bicycle.utils.MySelectorDecorator;
import com.school.bicycle.utils.OneDayDecorator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

public class SearchActivity extends BaseToolBarActivity {


    @BindView(R.id.calendarView)
    MaterialCalendarView myCalendar;

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setToolbarText("搜索");
        myCalendarInit();//初始化日历


    }

    private void myCalendarInit() {
        myCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        myCalendar.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large);
        myCalendar.setDateTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        myCalendar.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        myCalendar.setSelected(false);
        myCalendar.setEnabled(false);
        myCalendar.setClickable(false);
        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
        init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1, list);
        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                myCalendar.setDateSelected(date, false);
                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().getMonth() + 1, list);

            }
        });
        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);


    }
    public void init(int year, int month, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            Date date = new Date(year - 1900, month - 1, i + 1);
            if (list.get(i).equals("1")) {
                myCalendar.setDateSelected(date, true);
            }else {
                myCalendar.setDateSelected(date, false);
            }
        }
    }




}
