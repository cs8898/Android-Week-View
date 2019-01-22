package com.alamkanak.weekview.sample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alamkanak.weekview.TextColorPicker;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class PriorityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface customTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway/Raleway-Medium.ttf");
        mWeekView.setTypeface(customTypeface);
        mWeekView.setTextColorPicker(new TextColorPicker() {
            @Override
            public int getTextColor(WeekViewEvent event) {
                int color = event.getColor();
                double a = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
                return a < 0.2 ? Color.BLACK : Color.WHITE;
            }
        });

        TextView mDragableView = findViewById(R.id.draggable_view);
        mDragableView.setText(getString(R.string.toggle_priority));
        mWeekView.setPriorityOrdering(true);
        mDragableView.setBackgroundColor(Color.GREEN);
        mDragableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.draggable_view){
                    if (mWeekView.getPriorityOrdering()) {
                        mWeekView.setPriorityOrdering(false);
                        v.setBackgroundColor(Color.RED);
                    } else {
                        mWeekView.setPriorityOrdering(true);
                        v.setBackgroundColor(Color.GREEN);
                    }
                    mWeekView.notifyDatasetChanged();
                }
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        int[] colors = {getResources().getColor(R.color.event_color_01),
                getResources().getColor(R.color.event_color_02),
                getResources().getColor(R.color.event_color_03)};
        Random rnd = new Random();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 2);
        startTime.set(Calendar.MINUTE,0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR,4);
        WeekViewEvent event = new WeekViewEvent("event01","P0 Evnt 1",startTime,endTime);
        event.setColor(colors[0]);
        event.setPriority(0);
        events.add(event);

        //startTime = (Calendar) startTime.clone();
        //endTime = (Calendar) endTime.clone();
        event = new WeekViewEvent("event1A","P1 Evnt A","SOME LOCATION",startTime,endTime,true);
        event.setColor(colors[1]);
        event.setPriority(1);
        events.add(event);

        startTime = (Calendar) startTime.clone();
        endTime = (Calendar) endTime.clone();
        startTime.add(Calendar.HOUR,2);
        event = new WeekViewEvent("event02","P0 Evnt 2",startTime,endTime);
        event.setColor(colors[0]);
        event.setPriority(0);
        events.add(event);

        startTime = (Calendar) startTime.clone();
        endTime = (Calendar) endTime.clone();
        startTime.add(Calendar.HOUR,1);
        event = new WeekViewEvent("event11","P1 Evnt 1",startTime,endTime);
        event.setColor(colors[1]);
        event.setPriority(1);
        events.add(event);

        startTime = (Calendar) startTime.clone();
        endTime = (Calendar) endTime.clone();
        startTime.add(Calendar.HOUR,20);
        endTime.add(Calendar.HOUR,20);
        event = new WeekViewEvent("event21","P2 Evnt 1",startTime,endTime);
        event.setColor(colors[2]);
        event.setPriority(2);
        events.add(event);

        startTime = (Calendar) startTime.clone();
        endTime = (Calendar) endTime.clone();
        startTime.add(Calendar.HOUR,1);
        endTime.add(Calendar.HOUR,1);
        event = new WeekViewEvent("event13","P0 Evnt 3",startTime,endTime);
        event.setColor(colors[0]);
        event.setPriority(0);
        events.add(event);
        /*Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent("First", getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent("Second", getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        //AllDay event
        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(7, getEventTitle(startTime), null, startTime, endTime, true);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 2);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 10);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        // All day event until 00:00 next day
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 10);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 11);
        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(22, getEventTitle(startTime), startTime, endTime);
        event.setPriority(rnd.nextInt(2));
        event.setName(String.format("P%02d %s",event.getPriority(), event.getName()));
        event.setColor(colors[event.getPriority()]);
        events.add(event);*/

        return events;
    }
}
