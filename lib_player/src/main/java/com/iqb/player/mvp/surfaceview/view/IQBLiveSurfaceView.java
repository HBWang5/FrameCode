package com.iqb.player.mvp.surfaceview.view;

import android.content.Context;

import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.mvp.player.IIQBLivePlayer;
import com.iqb.player.mvp.player.proxy.IQBLivePlayerProxy;
import com.iqb.player.mvp.surfaceview.contract.IQBLiveSurfaceContract;
import com.iqb.player.mvp.surfaceview.presenter.IQBLiveSurfaceViewPresenter;

public class IQBLiveSurfaceView extends IQBLiveSurfaceContract.IQBLiveGroupRelativeView<IQBLiveSurfaceViewPresenter> {
    public IQBLiveSurfaceView(Context context) {
        super(context);
    }

    private IQBLivePlayerProxy mIQBLivePlayerProxy;
    private IQBLiveControllerView mIIQBLiveController;

    @Override
    protected IQBLiveSurfaceViewPresenter bindPresenter() {
        return new IQBLiveSurfaceViewPresenter();
    }

    /**
     * 显示
     */
    public void viewCreated() {
        //TODO:创建View视图容器
        mIQBLivePlayerProxy.setDisplay(this);
    }


    public void addCallback(IQBLivePlayerProxy mIQBLivePlayerProxy) {
        this.mIQBLivePlayerProxy = mIQBLivePlayerProxy;
        viewCreated();
    }


    @Override
    public IIQBLivePlayer getLivePlayer() {
        return mIQBLivePlayerProxy;
    }

    /**
     * 绑定控制器
     */
    @Override
    public void bindLiveController(IQBLiveControllerView mIIQBLiveController) {
        this.mIIQBLiveController = mIIQBLiveController;
    }
}
