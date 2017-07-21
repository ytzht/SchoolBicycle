package com.school.bicycle.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.school.bicycle.R;

import java.util.Date;



/**
 * Created by ytzht on 2017/07/21 下午10:11
 */

public class SelectDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private CalendarDay date;

    public SelectDecorator(Activity context, Date date) {
        this.date = CalendarDay.from(date);
        drawable = context.getResources().getDrawable(R.drawable.my_selectorss);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(drawable);
    }

}
