package com.iqb.player.mvp.mediagroup.contract;

import android.content.Context;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;

public interface IQBMediaGroupContract {
    abstract class IQBMediaGroupRelativeView<P extends IQBLiveGroupContractPresenter> extends IBaseContract.BaseFrameLayoutLiveView<P> implements BaseGroup {

        public IQBMediaGroupRelativeView(Context context) {
            super(context);
        }
        /**
         * 初始化播放器
         */
        public abstract void init();

        /**
         * 绑定播放器视图
         */
        public abstract void bindSurfaceView();

        /**
         * 获取当前播放器视图
         */
        public abstract IQBMediaSurfaceView getSurfaceView();

        /**
         * 绑定控制器
         */
        public abstract void bindIQBMediaController(IBaseIQBController mIIQBController);

    }

    abstract class IQBLiveGroupContractPresenter<V extends IQBMediaGroupRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
