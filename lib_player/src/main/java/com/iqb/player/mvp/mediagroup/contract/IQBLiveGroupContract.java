package com.iqb.player.mvp.mediagroup.contract;

import android.content.Context;
import android.widget.LinearLayout;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.surfaceview.view.IQBLiveSurfaceView;

public interface IQBLiveGroupContract {
    abstract class IQBLiveGroupRelativeView<P extends IQBLiveGroupContractPresenter> extends IBaseContract.BaseFrameLayoutLiveView<P> implements BaseGroup {

        public IQBLiveGroupRelativeView(Context context) {
            super(context);
        }

        public abstract void bindIQBMediaController(IBaseIQBController mIIQBController);

        public abstract IQBLiveSurfaceView getLiveViewGroup();


        public abstract void bindLiveViewGroup();

        public abstract void init();

        public abstract void deleteBlackTitleBar();

        public abstract void addBlackTitleBar();
    }

    abstract class IQBLiveGroupContractPresenter<V extends IQBLiveGroupRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
