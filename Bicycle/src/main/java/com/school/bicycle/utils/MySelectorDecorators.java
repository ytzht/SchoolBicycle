package com.school.bicycle.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.school.bicycle.R;




/**
 * Use a custom selector
 */
public class MySelectorDecorators implements DayViewDecorator {

    private final Drawable drawable;

    public MySelectorDecorators(Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.my_selectors);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
