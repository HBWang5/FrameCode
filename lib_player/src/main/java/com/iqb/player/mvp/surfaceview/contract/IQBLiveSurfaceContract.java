package com.iqb.player.mvp.surfaceview.contract;

import android.content.Context;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.mvp.player.IIQBLivePlayer;

public interface IQBLiveSurfaceContract {
    abstract class IQBLiveGroupRelativeView<P extends IQBLiveGroupContractPresenter> extends IBaseContract.BaseRelativeLayoutLiveView<P> implements IQBSurfaceView {

        public IQBLiveGroupRelativeView(Context context) {
            super(context);
        }

        public abstract IIQBLivePlayer getLivePlayer();

        public abstract void bindLiveController(IQBLiveControllerView mIQBSurfaceView);

    }

    abstract class IQBLiveGroupContractPresenter<V extends IQBLiveGroupRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
