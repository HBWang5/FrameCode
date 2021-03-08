package com.iqb.player.mvp.mediacontroller.contract;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceHolder;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;
import com.iqb.player.threadpro.IMediaProCallBack;

public interface IQBMusicControllerContract {
    abstract class IQBMusicControllerRelativeView<P extends IQBMusicControllerContractPresenter> extends IBaseContract.BaseRelativeLayoutLiveView<P> implements IBaseIQBController {

        public IQBMusicControllerRelativeView(Context context) {
            super(context);
        }

        public abstract void playMedia(String url);

        public abstract void surfaceCreated(SurfaceHolder holder);

        public abstract void surfaceChanged(SurfaceHolder holder, int format, int width, int height);

        public abstract void surfaceDestroyed(SurfaceHolder holder);


        /**
         * 开始播放
         */
        public abstract void start();

        /**
         * 暂停播放
         */
        public abstract void pause();


        /**
         * 恢复播放
         */
        public abstract void resume();

        /**
         * 准备
         */
        public abstract void prepare();

        /**
         * 获取播放当前位置
         */
        public abstract int getCurrentPosition();

        /**
         * 绑定播放器（进度条）管理器
         */
        public abstract void bindMediaProManager(IMediaProCallBack iMediaProCallBack);

        /**
         * 视频移动到指定位置
         */
        public abstract void seekTo(int progress);

        public abstract IQBMediaSurfaceView getSurfaceView();

        public abstract void setAppBrightness(int brightness);

        public abstract void setVolumeGesture(int volume);

        public abstract void showUI();

        public abstract void playerFull(Activity context);

        public abstract void pullMeasureLayout();

    }

    abstract class IQBMusicControllerContractPresenter<V extends IQBMusicControllerRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
