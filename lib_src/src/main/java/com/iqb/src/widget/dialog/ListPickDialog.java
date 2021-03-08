package com.iqb.src.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatDialog;

import com.iqb.src.R;
import com.iqb.src.widget.pick.CalendarDayView;

import java.text.SimpleDateFormat;
import java.util.Objects;


public class ListPickDialog<T> extends AppCompatDialog {

    private ListPickDialog(Builder<T> builder) {
        super(builder.context, R.style.DialogStyles);
        setContentView(R.layout.dialog_time_picker);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);


        CalendarDayView mCalendarDayView = findViewById(R.id.pick_value_view);
        TextView tvDialogTitle = findViewById(R.id.tv_dialog_title);
        TextView cancelTv = findViewById(R.id.cancel_tv);
        TextView countersignTv = findViewById(R.id.countersign_tv);

        long currentTime = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assert mCalendarDayView != null;
        mCalendarDayView.setDate(simpleDateFormat.format(currentTime));

        mCalendarDayView.setOnDateSelectedListener(builder.onDateSelectedListener);
        assert cancelTv != null;
        cancelTv.setOnClickListener(v -> {
            ListPickDialog.this.hide();
            ListPickDialog.this.dismiss();
            builder.onCancelClickListener.onClick(v);
        });
        assert countersignTv != null;
        countersignTv.setOnClickListener(v -> {
            mCalendarDayView.refreshData();
            ListPickDialog.this.hide();
            ListPickDialog.this.dismiss();
            builder.onCountersignClickListener.onClick(v);
        });


        cancelTv.setOnClickListener(v -> {
            ListPickDialog.this.hide();
            ListPickDialog.this.dismiss();
            builder.onCountersignClickListener.onClick(v);
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

    public static class Builder<T> {
        CalendarDayView.OnDateSelectedListener onDateSelectedListener;
        Context context;
        private String titleTv;

        private View.OnClickListener onCancelClickListener;
        private View.OnClickListener onCountersignClickListener;

        public Builder initTitle(String title) {
            titleTv = title;
            return this;
        }

        public Builder onDateSelectedListener(CalendarDayView.OnDateSelectedListener onDateSelectedListener) {
            this.onDateSelectedListener = onDateSelectedListener;
            return this;
        }

        public Builder initCancelClick(View.OnClickListener onCancelClickListener) {
            this.onCancelClickListener = onCancelClickListener;
            return this;
        }

        public Builder initCountersignClick(View.OnClickListener onCountersignClickListener) {
            this.onCountersignClickListener = onCountersignClickListener;
            return this;
        }


        public Builder(Context context) {
            this.context = context;
        }

        public ListPickDialog create() {
            return new ListPickDialog<T>(this);
        }
    }


}
