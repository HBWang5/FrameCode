package com.iqb.player.mvp.mediagroup.view;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iqb.player.R;
import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.mediagroup.contract.IQBLiveGroupContract;
import com.iqb.player.mvp.mediagroup.presenter.IQBLiveGroupPresenter;
import com.iqb.player.mvp.player.proxy.IQBLivePlayerProxy;
import com.iqb.player.mvp.surfaceview.view.IQBLiveSurfaceView;

/**
 * ----------Dragon be here!----------/
 * 播放器（直播）
 */
public class IQBLiveGroup extends IQBLiveGroupContract.IQBLiveGroupRelativeView<IQBLiveGroupPresenter> {
    private IQBLiveSurfaceView iqbLiveRelativeLayout;
    private LinearLayout linearLayout;

    public IQBLiveGroup(Context context) {
        super(context);
    }

    @Override
    public void bindIQBMediaController(IBaseIQBController mIIQBController) {
        this.addView((RelativeLayout) mIIQBController);
        iqbLiveRelativeLayout.bindLiveController((IQBLiveControllerView) mIIQBController);
        mIIQBController.initConstituteView(this);
    }

    @Override
    public void bindLiveViewGroup() {


        //初始化最底（直播）层容器布局
        iqbLiveRelativeLayout = new IQBLiveSurfaceView(getContext());
        //添加布局
        this.addView(iqbLiveRelativeLayout);
        //初始化直播播放器代理对象
        IQBLivePlayerProxy mIQBLivePlayerProxy = new IQBLivePlayerProxy(getContext());
        //注入播放器容器layout
        iqbLiveRelativeLayout.addCallback(mIQBLivePlayerProxy);
    }

    @Override
    public void init() {

    }

    @Override
    public void deleteBlackTitleBar() {
        if (linearLayout != null) {
            this.removeView(linearLayout);
            linearLayout = null;
        }
    }

    @Override
    public void addBlackTitleBar() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setBackgroundColor(Color.BLACK);
        this.addView(linearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.y80)));
    }

    @Override
    public IQBLiveSurfaceView getLiveViewGroup() {
        return iqbLiveRelativeLayout;
    }


    @Override
    protected IQBLiveGroupPresenter bindPresenter() {
        return new IQBLiveGroupPresenter();
    }

}
