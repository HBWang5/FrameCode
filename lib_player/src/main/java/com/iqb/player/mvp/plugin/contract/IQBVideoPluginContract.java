package com.iqb.player.mvp.plugin.contract;

import android.content.Context;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.plugin.IPluginUI;

public interface IQBVideoPluginContract {
    abstract class IQBVideoPluginRelativeView<P extends IQBVideoPluginContractPresenter> extends IBaseContract.BaseRelativeLayoutLiveView<P> implements IPluginUI {

        public IQBVideoPluginRelativeView(Context context) {
            super(context);
        }

    }

    abstract class IQBVideoPluginContractPresenter<V extends IQBVideoPluginRelativeView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
