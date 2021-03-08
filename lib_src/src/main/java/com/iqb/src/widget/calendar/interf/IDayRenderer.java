package com.iqb.src.widget.calendar.interf;

import android.graphics.Canvas;

import com.iqb.src.widget.calendar.view.Day;


public interface IDayRenderer {

    void refreshContent();

    void drawDay(Canvas canvas, Day day);

    IDayRenderer copy();

}
