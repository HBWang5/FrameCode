package com.iqb.player.tools;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.iqb.player.adapter.LiveContentMsgAdapter;
import com.iqb.src.widget.IQBPlayerRecyclerView;

public class TimeMsgTools {
    private boolean isVisible = false;
    private static TimeMsgTools mTimeTools;
    private IQBPlayerRecyclerView iqbPlayerRecyclerView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isVisible = false;
            iqbPlayerRecyclerView.setVisibility(View.INVISIBLE);
            liveContentMsgAdapter.clearData();
        }
    };
    private LiveContentMsgAdapter liveContentMsgAdapter;


    private TimeMsgTools() {
    }

    public boolean setView(IQBPlayerRecyclerView iqbPlayerRecyclerView, LiveContentMsgAdapter liveContentMsgAdapter) {
        this.iqbPlayerRecyclerView = iqbPlayerRecyclerView;
        this.liveContentMsgAdapter = liveContentMsgAdapter;

        boolean isVisible = iqbPlayerRecyclerView.getVisibility() == View.INVISIBLE;
        iqbPlayerRecyclerView.setFocusable(true);
        iqbPlayerRecyclerView.setVisibility(View.VISIBLE);
        init();
        return isVisible;
    }

    private void init() {
        isVisible = true;
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(0, 5000);
    }

    public static synchronized TimeMsgTools getInstance() {
        if (mTimeTools == null) {
            mTimeTools = new TimeMsgTools();
        }
        return mTimeTools;
    }


}
