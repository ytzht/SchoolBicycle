package com.school.bicycle.ui.search;

import android.os.Bundle;
import android.util.Log;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;
import com.school.bicycle.R;
import com.school.bicycle.global.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseToolBarActivity implements com.andexert.calendarlistview.library.DatePickerController{

    @BindView(R.id.pickerView)
    DayPickerView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setToolbarText("搜索");
        pickerView.setController(this);

    }

    @Override
    public int getMaxYear() {
        return 2018;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        Log.e("Day Selected=", day + " / " + month + " / " + year);
        //得到当前选择的时间
    }



    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        Log.e("Date range selected=", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
        //得到当前选择的时间范围
    }
}
