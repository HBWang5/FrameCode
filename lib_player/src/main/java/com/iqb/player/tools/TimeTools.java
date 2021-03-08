package com.iqb.player.tools;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;


import com.iqb.player.R;
import com.iqb.src.widget.IQBPlayerTextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTools {
    private int time = 0;
    private static TimeTools mTimeTools;
    private IQBPlayerTextView timeTv;
    private TimerTask timerTask;
    private Timer timer;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (timeTv == null) {
                return;
            }
            if (!(boolean) timeTv.getTag()) {
                timeTv.setText("");
                return;
            }

            timeTv.setText(stringBuilder);
            timeTv.setPadding(timeTv.getContext().getResources().getDimensionPixelSize(R.dimen.x88), 0, 0, 0);
        }
    };
    private String stringBuilder;

    public void setTimeTv(IQBPlayerTextView timeTv) {
        this.timeTv = timeTv;
        if (timer == null || timerTask == null) {
            init();
        }
    }

    private void init() {
        time = 0;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                handler.sendEmptyMessage(0);
                int hour = (time / 4) / 60 / 60;
                int minute = (time / 4) / 60;
                int second = (time / 4) % 60;
                stringBuilder = (hour >= 10 ? hour : "0" + hour) + ":" +
                        (minute >= 10 ? minute : "0" + minute) + ":" +
                        (second >= 10 ? second : "0" + second);


            }
        };
//        timer.schedule(timerTask, 1000, 1000);
        timer.scheduleAtFixedRate(timerTask, 250, 250);
    }

    public void cancel() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        if (timer != null) {
            timer.cancel();
        }
        timerTask = null;
        timer = null;

    }

    private TimeTools() {

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                handler.sendEmptyMessage(0);
                int hour = (time / 4) / 60 / 60;
                int minute = (time / 4) / 60;
                int second = (time / 4) % 60;
                stringBuilder = (hour >= 10 ? hour : "0" + hour) + ":" +
                        (minute >= 10 ? minute : "0" + minute) + ":" +
                        (second >= 10 ? second : "0" + second);


            }
        };
//        timer.schedule(timerTask, 1000, 1000);
        timer.scheduleAtFixedRate(timerTask, 250, 250);
    }

    public static synchronized TimeTools getInstance() {
        if (mTimeTools == null) {
            mTimeTools = new TimeTools();
        }
        return mTimeTools;
    }


    public void setVisible() {
        handler.sendEmptyMessage(0);
    }
}
