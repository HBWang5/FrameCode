package com.iqb.player.mvp.base;


import android.content.Context;

import androidx.annotation.NonNull;


public interface IBaseContract {
    abstract class BaseRelativeLayoutLiveView<P extends IBaseLivePresenter> extends IBaseRelativeLayoutLiveView<P> {

        public BaseRelativeLayoutLiveView(Context context) {
            super(context);
        }
    }

    abstract class BaseFrameLayoutLiveView<P extends IBaseLivePresenter> extends IBaseFrameLayoutLiveView<P> {

        public BaseFrameLayoutLiveView(Context context) {
            super(context);
        }
    }

    abstract class BaseSurfaceLiveView<P extends IBaseLivePresenter> extends IBaseSurfaceLiveView<P> {

        public BaseSurfaceLiveView(@NonNull Context context) {
            super(context);
        }
    }

    abstract class BasePlayerLiveView<P extends IBaseLivePresenter> extends IBaseLiveView<P> {
    }

    abstract class BaseLivePresenter<V extends BaseLiveView> extends IBaseLivePresenter<V> {
    }
}
