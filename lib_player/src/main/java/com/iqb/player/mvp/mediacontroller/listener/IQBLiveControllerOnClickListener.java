package com.iqb.player.mvp.mediacontroller.listener;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iqb.api.utils.UtilFastClick;
import com.iqb.player.R;
import com.iqb.player.mvp.mediacontroller.presenter.IQBLiveControllerPresenter;
import com.iqb.player.tools.TimeTools;
import com.iqb.src.widget.IQBPlayerLinearLayout;

public class IQBLiveControllerOnClickListener extends RecyclerView.OnScrollListener implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static IQBLiveControllerOnClickListener mIQBLiveControllerOnClickListener;
    private IQBLiveControllerPresenter iqbLiveControllerPresenter;

    private IQBLiveControllerOnClickListener() {

    }

    public static synchronized IQBLiveControllerOnClickListener getInstance() {
        if (mIQBLiveControllerOnClickListener == null) {
            mIQBLiveControllerOnClickListener = new IQBLiveControllerOnClickListener();
        }
        return mIQBLiveControllerOnClickListener;
    }

    public void bindController(IQBLiveControllerPresenter iqbLiveControllerPresenter) {
        this.iqbLiveControllerPresenter = iqbLiveControllerPresenter;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.class_time_img) {
            v.setBackgroundResource(!(boolean) v.getTag() ? R.drawable.live_class_btn_time_selected : R.drawable.live_class_btn_time_normal);
            if (!(boolean) v.getTag()) {
                IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(v.getContext().getResources().getDimensionPixelSize(R.dimen.x322), v.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
                imageViewLayoutParams.topMargin = v.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
                v.setLayoutParams(imageViewLayoutParams);
            } else {
                IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(v.getContext().getResources().getDimensionPixelSize(R.dimen.x88), v.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
                imageViewLayoutParams.topMargin = v.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
                v.setLayoutParams(imageViewLayoutParams);
            }
            v.setTag(!(boolean) v.getTag());
            if ((boolean) v.getTag()) {
                TimeTools.getInstance().setVisible();
            }
        }  else if (i == R.id.staff_icon_img) {
            iqbLiveControllerPresenter.checkScore();
        } else if (i == R.id.stand_up_img) {
            UtilFastClick.setMinClickDelayTime(1000);
            if (!UtilFastClick.isFastClick()) {
                return;
            }
            iqbLiveControllerPresenter.handsUp();
        } else if (i == R.id.loudspeaker_img) {
            v.setTag(!((Boolean) v.getTag()));
            iqbLiveControllerPresenter.openCloseLoudspeaker((Boolean) v.getTag());
            if ((Boolean) v.getTag()) {
                v.setBackgroundResource(R.drawable.live_loudspeaker_open_icon);
            } else {
                v.setBackgroundResource(R.drawable.live_loudspeaker_close_icon);
            }
        } else if (i == R.id.microphone_img) {
            v.setTag(!((Boolean) v.getTag()));
            if ((Boolean) v.getTag()) {
                v.setBackgroundResource(R.drawable.live_microphone_open_icon);
            } else {
                v.setBackgroundResource(R.drawable.live_microphone_close_icon);
            }
            iqbLiveControllerPresenter.openCloseMicrophone((Boolean) v.getTag());
        } else if (i == R.id.leave_channel_img) {
            UtilFastClick.setMinClickDelayTime(1000);
            if (!UtilFastClick.isFastClick()) {
                return;
            }
            iqbLiveControllerPresenter.leaveChannel();
        }


    }


    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //获取第一个可见view的位置
            int firstItemPosition = linearManager.findFirstVisibleItemPosition();
            //获取最后一个可见view的位置
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            iqbLiveControllerPresenter.notifyMsg(lastItemPosition);
        }
    }
}