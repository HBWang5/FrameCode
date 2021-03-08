package com.iqb.player.mvp.surfaceview.contract;


import android.content.Context;

import androidx.annotation.NonNull;

import com.iqb.player.mvp.surfaceview.listener.IIQBVideoGestureListener;
import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.surfaceview.ISurfaceViewCallBack;

public interface IQBMediaSurfaceContract {
    abstract class IQBMediaSurfaceRelativeView<P extends IQBMediaSurfaceContractPresenter> extends IBaseContract.BaseSurfaceLiveView<P> implements IQBSurfaceView, ISurfaceViewCallBack, IIQBVideoGestureListener {
        public IQBMediaSurfaceRelativeView(@NonNull Context context) {
            super(context);
        }

        public abstract IIQBMediaPlayer getMediaPlayer();

        public abstract void setSyncProgress(long position);

        public abstract void bindMediaController(IQBVideoControllerView mHBWangMediaController);
    }

    abstract class IQBMediaSurfaceContractPresenter<V extends IQBMediaSurfaceRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
