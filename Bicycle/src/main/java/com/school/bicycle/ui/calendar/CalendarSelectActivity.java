package com.school.bicycle.ui.calendar;

import com.school.bicycle.global.BaseToolBarActivity;

public class CalendarSelectActivity extends BaseToolBarActivity {
//
//    private static final String TAG = "SignHistoryAct=====";
//    private MaterialCalendarView myCalendar;
//    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();//今天
//    private TextView tv_ok;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar_select);
//
//        tv_ok = (TextView) findViewById(R.id.tv_ok);
//        myCalendar = (MaterialCalendarView) findViewById(R.id.calendar_md);
//        myCalendarInit();//初始化日历
//
//
//        tv_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                List<CalendarDay> selectedDates = myCalendar.getSelectedDates();
//
//                if (selectedDates.size()>0) {
//                    for (int i = 0; i < selectedDates.size(); i++) {
//                        Log.d(TAG, "onClick: "+selectedDates.get(i).getDate().getTime());
//                    }
//
//                }else {
//                    showShort("请选择日期");
//                }
//            }
//        });
//    }
//    DateFormat format;
//    public void myCalendarInit() {
//        myCalendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
//        myCalendar.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Small);
//        myCalendar.setDateTextAppearance(R.style.TextAppearance_AppCompat_Small);
//        myCalendar.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Small);
//        myCalendar.setSelected(false);
//        myCalendar.setEnabled(false);
//        myCalendar.setClickable(false);
//        CalendarDay today = CalendarDay.today();
//        myCalendar.state().edit()
//                .setMinimumDate(CalendarDay.today())
//                .setMaximumDate(CalendarDay.from(today.getYear(), today.getMonth() + 1, today.getDay()))
//                .commit();
//        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
////        init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);
//        myCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
//            @Override
//            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
////                signDataInit(date.getYear(), date.get月租() + 1);
//            }
//        });
//        myCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
//                if (selected) {
//                    myCalendar.setDateSelected(date, true);
//                }else {
//                    myCalendar.setDateSelected(date, false);
//                }
////                init(myCalendar.getCurrentDate().getYear(), myCalendar.getCurrentDate().get月租() + 1, list);
//
//            }
//        });
//
//
//        myCalendar.addDecorators(new MySelectorDecorator(this), new HighlightWeekendsDecorator(this), oneDayDecorator);
//
//
//
}
