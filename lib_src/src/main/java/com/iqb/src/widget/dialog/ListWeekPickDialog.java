package com.iqb.src.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.iqb.src.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;


public class ListWeekPickDialog<T> extends AppCompatDialog {

    private ArrayList weekData;
    private final ArrayList<String> arrayList;
    private long yearDataTime;
    private long weekDataTime;
    private String weekDataTimeStr;
    private int currentWeek;

    private ListWeekPickDialog(Builder<T> builder) {
        super(builder.context, R.style.DialogStyles);
        setContentView(R.layout.dialog_time_week_picker);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        WheelView yearTime = findViewById(R.id.year_time);
        assert yearTime != null;
        yearTime.setTextSize(builder.context.getResources().getDimensionPixelSize(R.dimen.x15));
        WheelView weekTime = findViewById(R.id.week_time);
        assert weekTime != null;
        weekTime.setTextSize(builder.context.getResources().getDimensionPixelSize(R.dimen.x15));
        TextView tvDialogTitle = findViewById(R.id.tv_dialog_title);
        TextView cancelTv = findViewById(R.id.cancel_tv);
        TextView countersignTv = findViewById(R.id.countersign_tv);


        yearTime.setCyclic(false);
        weekTime.setCyclic(false);
        ArrayList<String> yearData = new ArrayList<>(builder.timeData.keySet());
        weekData = (ArrayList) builder.timeData.get(yearData.get(yearData.size() - 1));
        arrayList = new ArrayList<>();
        currentWeek = 0;
        for (int i = 0; i < weekData.size(); i++) {
            arrayList.add("第" + (i + 1) + "周" + " " + timeStamp2Date(Long.parseLong(weekData.get(i) + ""), null) + "-" + timeStamp2Date(Long.parseLong(weekData.get(i) + "") + (60 * 60 * 24 * 7 * 1000), null));
            if (System.currentTimeMillis() < Long.parseLong(weekData.get(i) + "") && currentWeek == 0) {
                currentWeek = i;
            }
        }
        ArrayWheelAdapter wheelAdapterYear = new ArrayWheelAdapter(yearData);
        yearTime.setAdapter(wheelAdapterYear);
        ArrayWheelAdapter wheelAdapterWeek = new ArrayWheelAdapter(arrayList);
        weekTime.setAdapter(wheelAdapterWeek);

        yearTime.setCurrentItem(yearData.size() - 1);
        weekTime.setCurrentItem(currentWeek - 1);
        yearDataTime = Long.parseLong(yearData.get(yearData.size() - 1));
        weekDataTime = (long) weekData.get(currentWeek - 1);
        weekDataTimeStr = arrayList.get(currentWeek - 1);

        yearTime.setOnItemSelectedListener(index -> {

            yearDataTime = Long.parseLong(yearData.get(index));
            weekData = (ArrayList) builder.timeData.get(yearDataTime + "");
            arrayList.clear();
            assert weekData != null;
            for (int i = 0; i < weekData.size(); i++) {
                arrayList.add("第" + (i + 1) + "周" + " " + timeStamp2Date(Long.parseLong(weekData.get(i) + ""), null) + "-" + timeStamp2Date(Long.parseLong(weekData.get(i) + "") + (60 * 60 * 24 * 7 * 1000), null));
            }
            ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(arrayList);
            weekTime.setAdapter(arrayWheelAdapter);
            weekDataTime = (long) weekData.get(currentWeek - 1);
            weekDataTimeStr = arrayList.get(currentWeek - 1);
        });
        weekTime.setOnItemSelectedListener(index -> {
            weekDataTime = (long) weekData.get(index);
            weekDataTimeStr = arrayList.get(index);
        });
        assert cancelTv != null;
        cancelTv.setOnClickListener(v -> {
            ListWeekPickDialog.this.hide();
            ListWeekPickDialog.this.dismiss();
        });
        assert countersignTv != null;
        countersignTv.setOnClickListener(v -> {
            ListWeekPickDialog.this.hide();
            ListWeekPickDialog.this.dismiss();
            builder.countersignClick.onCountersignClickListener(yearDataTime, weekDataTime, weekDataTimeStr);
        });


        if (!TextUtils.isEmpty(builder.titleTv)) {
            assert tvDialogTitle != null;
            tvDialogTitle.setText(builder.titleTv);
        }
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setWindowAnimations(R.style.BottomDialogAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);
    }

    public interface CountersignClick {
        void onCountersignClickListener(long yearDataTime, long weekDataTime, String weekDataTimeStr);
    }

    public static class Builder<T> {
        Context context;
        LinkedHashMap<String, List> timeData;
        private String titleTv;
        private CountersignClick countersignClick;

        public Builder initTitle(String title) {
            titleTv = title;
            return this;
        }


        public Builder initCountersignClick(CountersignClick countersignClick) {
            this.countersignClick = countersignClick;
            return this;
        }


        public Builder(Context context, LinkedHashMap<String, List> timeData) {
            this.context = context;
            this.timeData = timeData;
        }

        public ListWeekPickDialog create() {
            return new ListWeekPickDialog<T>(this);
        }
    }

    /**
     * 时间戳转日期
     */
    private static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "MM.dd";
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    class ArrayWheelAdapter implements WheelAdapter {
        ArrayList<String> strings;

        ArrayWheelAdapter(ArrayList<String> strings) {
            this.strings = strings;
        }

        @Override
        public int getItemsCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int index) {
            return strings.get(index);
        }

        @Override
        public int indexOf(Object o) {
            return strings.indexOf(o);
        }
    }
}
