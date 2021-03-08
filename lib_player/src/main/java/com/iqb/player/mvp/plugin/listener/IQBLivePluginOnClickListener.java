package com.iqb.player.mvp.plugin.listener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.iqb.player.R;
import com.iqb.player.mvp.plugin.presenter.LivePluginPresenter;


public class IQBLivePluginOnClickListener implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static IQBLivePluginOnClickListener mIQBLivePluginOnClickListener;
    private LivePluginPresenter livePluginPresenter;

    private IQBLivePluginOnClickListener() {

    }

    public static synchronized IQBLivePluginOnClickListener getInstance() {
        if (mIQBLivePluginOnClickListener == null) {
            mIQBLivePluginOnClickListener = new IQBLivePluginOnClickListener();
        }
        return mIQBLivePluginOnClickListener;
    }

    public void bindController(LivePluginPresenter livePluginPresenter) {
        this.livePluginPresenter = livePluginPresenter;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.down_up_img) {
            v.setTag(!((Boolean) v.getTag()));
            if ((Boolean) v.getTag()) {
                v.setBackgroundResource(R.drawable.live_class_down_icon);
            } else {
                v.setBackgroundResource(R.drawable.live_class_up_icon);
            }
            livePluginPresenter.visiblePreImgList((Boolean) v.getTag());
        } else if (i == R.id.back_live_img) {
            livePluginPresenter.returnVideo();
        } else if (i == R.id.img_select_tv) {
            String tag = (String) v.getTag();
            String replace = tag.trim().replace(",,", ",");
            if (replace.indexOf(",") == 0) {
                replace = replace.substring(1);
            }
            if (replace.indexOf(",") == replace.length()) {
                replace = replace.substring(0, replace.length() - 1);
            }
            livePluginPresenter.removeImg(replace);
        } else if (i == R.id.class_prepare_item_img) {
            livePluginPresenter.intentPrepare();
        }
    }
}
