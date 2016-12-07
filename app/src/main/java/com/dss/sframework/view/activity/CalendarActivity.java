package com.dss.sframework.view.activity;

import android.support.v7.app.ActionBarActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import com.dss.sframework.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by digipronto on 23/11/16.
 */

@EActivity(R.layout.activity_calendar)
public class CalendarActivity extends ActionBarActivity {

    @ViewById
    CalendarView calendarView;

    @ViewById(R.id.date_display)
    TextView dateDisplay;

    @AfterViews
    void afterViews(){
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);
            }
        });
    }
}
