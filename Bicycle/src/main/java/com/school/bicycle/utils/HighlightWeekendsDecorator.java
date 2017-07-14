package com.school.bicycle.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

import cn.fcrj.volunteer1.R;

/**
 * Highlight Saturdays and Sundays with a background
 */
public class HighlightWeekendsDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#228BC34A");

    public HighlightWeekendsDecorator(Activity context) {
//        highlightDrawable = new ColorDrawable(color);
        highlightDrawable= context.getResources().getDrawable(R.drawable.sign_allbg);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return
                false;
//                weekDay == Calendar.SATURDAY
//                || weekDay == Calendar.SUNDAY
//                || weekDay == Calendar.MONDAY
//                || weekDay == Calendar.TUESDAY
//                || weekDay == Calendar.THURSDAY
//                || weekDay == Calendar.WEDNESDAY
//                || weekDay == Calendar.FRIDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
