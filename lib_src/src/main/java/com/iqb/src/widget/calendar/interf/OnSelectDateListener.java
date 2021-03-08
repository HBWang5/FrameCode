package com.iqb.src.widget.calendar.interf;


import com.iqb.src.widget.calendar.model.CalendarDate;

public interface OnSelectDateListener {
    void onSelectDate(CalendarDate date);

    void onSelectOtherMonth(int offset);//点击其它月份日期
}
