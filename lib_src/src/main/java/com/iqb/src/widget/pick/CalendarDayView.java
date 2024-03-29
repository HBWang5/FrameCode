package com.iqb.src.widget.pick;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.iqb.src.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CalendarDayView extends LinearLayout {

    /**
     * 年
     */
    private SpinnerView mYearSpinnerView;

    /**
     * 月
     */
    private SpinnerView mMonthSpinnerView;

    /**
     * 日
     */
    private SpinnerView mDaySpinnerView;

    /**
     * 文本颜色
     */
    private int mTextColor;

    /**
     * 文本大小
     */
    private int mTextSize;

    /**
     * 选中文本的颜色
     */
    private int mSelectedTextColor;

    /**
     * 选中线的颜色
     */
    private int mSelectedLineColor;

    /**
     * 选择的年, 月，日
     */
    private int mSelectYear, mSelectMonth, mSelectDay;

    /**
     * 时间linarlayout
     */
    private LinearLayout mLlDate;

    private OnDateSelectedListener mOnDateSelectedListener;

    public CalendarDayView(Context context) {
        super(context);
    }

    public CalendarDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CalendarDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        mOnDateSelectedListener = onDateSelectedListener;
    }


    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        initStyle(context, attrs);

        /**
         *  时间linearlayout布局
         */
        LayoutParams dateLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mLlDate = new LinearLayout(context);
        mLlDate.setOrientation(HORIZONTAL);
        mLlDate.setLayoutParams(dateLp);
        addView(mLlDate);

        View divisionLineView = new View(context);
        divisionLineView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divisionLineView.setBackgroundColor(mSelectedLineColor);
        addView(divisionLineView);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, 800);
        mYearSpinnerView = new SpinnerView(context);
        addViewData(mYearSpinnerView, lp, getYearList(), "年");

        mMonthSpinnerView = new SpinnerView(context);
        addViewData(mMonthSpinnerView, lp, getMonthList(), "月");

        mDaySpinnerView = new SpinnerView(context);
        addViewData(mDaySpinnerView, lp, new ArrayList<String>(), "日");

        setListener();
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarViewStyle);
        mTextColor = typedArray.getColor(R.styleable.CalendarViewStyle_textColor, Color.parseColor("#999999"));
        mSelectedTextColor = typedArray.getColor(R.styleable.CalendarViewStyle_selectedTextColor, Color.parseColor("#000000"));
        mSelectedLineColor = typedArray.getColor(R.styleable.CalendarViewStyle_selectedLineColor, Color.parseColor("#D6D6D6"));
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarViewStyle_selectedTextColor, getResources().getDimensionPixelSize(R.dimen.x30));
        typedArray.recycle();
    }

    private void setStyle(SpinnerView spinnerView) {
        spinnerView.setTextColor(mTextColor);
        spinnerView.setTextSize(mTextSize);
        spinnerView.setSelectedTextColor(mSelectedTextColor);
        spinnerView.setSelectLineColor(mSelectedLineColor);
        spinnerView.invalidate();
    }

    private void addViewData(SpinnerView spinnerView, LayoutParams lp, List<String> dataList, String unit) {
        spinnerView.setLayoutParams(lp);
        mLlDate.addView(spinnerView);
        spinnerView.setAllDataList(dataList);
        spinnerView.setUnit(unit);

        setStyle(spinnerView);
    }

    private void setListener() {
        mYearSpinnerView.setOnDataSelectedListener(data -> {
            mSelectYear = data;
            setDayAllDataAndCurrentData();
            mOnDateSelectedListener.onDateSelected(mSelectYear, mSelectMonth, mSelectDay);
        });
        mMonthSpinnerView.setOnDataSelectedListener(data -> {
            mSelectMonth = data;
            setDayAllDataAndCurrentData();
            mOnDateSelectedListener.onDateSelected(mSelectYear, mSelectMonth, mSelectDay);
        });
        mDaySpinnerView.setOnDataSelectedListener(data -> {
            mSelectDay = data;
            mOnDateSelectedListener.onDateSelected(mSelectYear, mSelectMonth, mSelectDay);
        });
    }

    public void setDate(String date) {
        String[] dateArray = date.split("-");
        switch (dateArray.length) {
            case 3:
                mSelectYear = Integer.valueOf(dateArray[0]);
                mSelectMonth = Integer.valueOf(dateArray[1]);
                mSelectDay = Integer.valueOf(dateArray[2]);
                break;
            case 2:
                mSelectYear = Integer.valueOf(dateArray[0]);
                mSelectMonth = Integer.valueOf(dateArray[1]);
                break;
            case 1:
                mSelectDay = Integer.valueOf(dateArray[0]);
                break;
        }
        mYearSpinnerView.setCurrentData(mSelectYear + "年");
        mMonthSpinnerView.setCurrentData((mSelectMonth > 9 ? mSelectMonth : "0" + mSelectMonth) + "月");
        setDayAllDataAndCurrentData();
    }

    private void setDayAllDataAndCurrentData() {
        List<String> dayList = getDayList(mSelectYear + "-" + (mSelectMonth > 9 ? mSelectMonth : "0" + mSelectMonth));
        mDaySpinnerView.setAllDataList(dayList);
        checkDayIsExist(dayList);
        mDaySpinnerView.setCurrentData((mSelectDay > 9 ? mSelectDay : "0" + mSelectDay) + "日");
    }

    private void checkDayIsExist(List<String> dayList) {
        String selectDayStr = (mSelectDay > 9 ? mSelectDay : "0" + mSelectDay) + "日";
        if (!dayList.contains(selectDayStr)) {
            mSelectDay--;
            checkDayIsExist(dayList);
        }
    }

    private List<String> getYearList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 1970; i < 2035; i++) {
            dataList.add(i + "年");
        }
        return dataList;
    }

    private List<String> getMonthList() {
        List<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add((i > 9 ? i : "0" + i) + "月");
        }
        return monthList;
    }

    private List<String> getDayList(String yearMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Objects.requireNonNull(getDate(yearMonth)));
        int monthMaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<String> dayList = new ArrayList<>();
        for (int i = 1; i <= monthMaxDay; i++) {
            dayList.add((i > 9 ? i : "0" + i) + "日");
        }
        return dayList;
    }

    private Date getDate(String dateStr) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void refreshData() {
        if (mOnDateSelectedListener != null) {
            mOnDateSelectedListener.onDateSelected(mSelectYear, mSelectMonth, mSelectDay);
        }
    }

    public interface OnDateSelectedListener {
        void onDateSelected(int year, int month, int day);
    }

}
