package com.iqb.player.mvp.player.contract;

import android.view.SurfaceHolder;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.player.listener.IIQBMediaPlayerListener;
import com.iqb.player.threadpro.IMediaProCallBack;

public interface IQBVideoPlayerContract {
    abstract class IQBVideoPlayerView<P extends IQBVideoContractPresenter> extends IBaseContract.BasePlayerLiveView<P> {

        /**
         * 显示
         */
        public abstract void setDisplay(SurfaceHolder display);

        /**
         * 准备播放器
         */
        public abstract void prepare();

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
         * 销毁
         */
        public abstract void onDestroy();


        /**
         * 保存播放状态和进度
         */
        public abstract void savePlayMediaState();


        /**
         * 清除播放状态和进度
         */
        public abstract void clearPlayMediaState();


        /**
         * 跳到指定的播放位置
         */
        public abstract  void seekTo(long pos);


        /**
         * 设置缓冲百分比
         */
        public abstract  void setBufferPercentage(int percent);

        /**
         * 获取缓冲百分比
         */
        public abstract int getBufferPercentage();

        /**
         * 移除播放状态监听器
         */
        public abstract void removeMediaPlayerListener(IIQBMediaPlayerListener listener);

        /**
         * 添加播放状态监听器
         */
        public abstract void addMediaPlayerListener(IIQBMediaPlayerListener listener);

        /**
         * 获取总时长
         */
        public abstract long getDuration();

        /**
         * 获取当前位置
         */
        public abstract long getCurrentPosition();

        /**
         * 播放视频
         */
        public abstract void playMedia(String url);

        /**
         * 绑定播放管理器
         */
        public abstract void bindMediaProManager(IMediaProCallBack iMediaProCallBack);

        /**
         * 播放器初始化准备完成
         */
        public abstract void onPrepared();
    }

    abstract class IQBVideoContractPresenter<V extends IQBVideoPlayerView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
