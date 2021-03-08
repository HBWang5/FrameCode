package com.iqb.player.mvp.mediagroup.view;

import android.content.Context;
import android.widget.RelativeLayout;

import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.mediagroup.contract.IQBMediaGroupContract;
import com.iqb.player.mvp.mediagroup.presenter.IQBMediaGroupPresenter;
import com.iqb.player.mvp.player.proxy.IQBMediaPlayerProxy;
import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;

/**
 * ----------Dragon be here!----------/
 * 播放器
 */
public class IQBMediaGroup extends IQBMediaGroupContract.IQBMediaGroupRelativeView<IQBMediaGroupPresenter> {
    private IQBMediaSurfaceView mSurfaceView;

    public IQBMediaGroup(Context context) {
        super(context);
    }

    @Override
    protected IQBMediaGroupPresenter bindPresenter() {
        return new IQBMediaGroupPresenter();
    }

    /**
     * 初始化播放器
     */
    @Override
    public void init() {


    }


    @Override
    public void bindSurfaceView() {
        //初始化surfaceView展示
        mSurfaceView = new IQBMediaSurfaceView(getContext());
        //添加布局
        this.addView(mSurfaceView);
        //初始化播放器代理
        IQBMediaPlayerProxy mIQBMediaPlayerProxy = new IQBMediaPlayerProxy();
        //surfaceView展示
        mSurfaceView.addCallback(mIQBMediaPlayerProxy);
    }

    @Override
    public IQBMediaSurfaceView getSurfaceView() {
        return mSurfaceView;
    }

    /**
     * 绑定控制器
     */
    @Override
    public void bindIQBMediaController(IBaseIQBController mIIQBController) {
        this.addView((RelativeLayout) mIIQBController);
        mSurfaceView.bindMediaController((IQBVideoControllerView) mIIQBController);
        mIIQBController.initConstituteView((BaseGroup) this);
    }
}
