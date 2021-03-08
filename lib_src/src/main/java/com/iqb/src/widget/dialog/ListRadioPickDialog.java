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
import java.util.Objects;


public class ListRadioPickDialog<T> extends AppCompatDialog {

    private int currentIndex = 0;

    private ListRadioPickDialog(Builder<T> builder) {
        super(builder.context, R.style.DialogStyles);
        setContentView(R.layout.dialog_time_radio_picker);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        WheelView weekTime = findViewById(R.id.select_time);
        assert weekTime != null;
        weekTime.setTextSize(builder.context.getResources().getDimensionPixelSize(R.dimen.x15));
        TextView tvDialogTitle = findViewById(R.id.tv_dialog_title);
        TextView cancelTv = findViewById(R.id.cancel_tv);
        TextView countersignTv = findViewById(R.id.countersign_tv);


        weekTime.setCyclic(false);
        ArrayWheelAdapter wheelAdapterWeek = new ArrayWheelAdapter(builder.listData);
        weekTime.setAdapter(wheelAdapterWeek);

//        weekTime.setCurrentItem(currentIndex - 1);
        weekTime.setCurrentItem(builder.indexOf);

        weekTime.setOnItemSelectedListener(index -> currentIndex = index);
        assert cancelTv != null;
        cancelTv.setOnClickListener(v -> {
            ListRadioPickDialog.this.hide();
            ListRadioPickDialog.this.dismiss();
        });
        assert countersignTv != null;
        countersignTv.setOnClickListener(v -> {
            ListRadioPickDialog.this.hide();
            ListRadioPickDialog.this.dismiss();
            builder.countersignClick.onCountersignClickListener(builder.listData.get(currentIndex));
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
        void onCountersignClickListener(String selectTimeStr);
    }

    public static class Builder<T> {
        Context context;
        ArrayList<String> listData;
        private String titleTv;
        private CountersignClick countersignClick;
        int indexOf;

        public Builder initTitle(String title) {
            titleTv = title;
            return this;
        }


        public Builder initCountersignClick(CountersignClick countersignClick) {
            this.countersignClick = countersignClick;
            return this;
        }


        public Builder(Context context, ArrayList<String> listData) {
            this.context = context;
            this.listData = listData;
        }

        public ListRadioPickDialog create() {
            return new ListRadioPickDialog<T>(this);
        }

        public Builder initSelectIndex(int indexOf) {
            this.indexOf = indexOf;
            return this;
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
