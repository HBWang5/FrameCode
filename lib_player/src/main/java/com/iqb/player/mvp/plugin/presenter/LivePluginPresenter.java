package com.iqb.player.mvp.plugin.presenter;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.iqb.api.utils.ToastUtils;
import com.iqb.been.event.LiveForPlayerBindImg;
import com.iqb.been.event.LiveForPlayerChangeImg;
import com.iqb.been.event.LiveForPlayerDrawClear;
import com.iqb.been.event.LiveForPlayerDrawImg;
import com.iqb.been.event.LiveForPlayerDrawPPT;
import com.iqb.been.event.LiveForPlayerIsPen;
import com.iqb.been.event.LiveForPlayerPPTPage;
import com.iqb.been.event.LiveForPlayerShowImg;
import com.iqb.been.event.LiveForPlayerShowPPT;
import com.iqb.been.event.LiveViewGoneImgList;
import com.iqb.been.event.LiveViewOpenPrepareImg;
import com.iqb.been.event.LiveViewRemoveImg;
import com.iqb.been.event.LiveViewReturnVideo;
import com.iqb.been.event.LiveViewVisibleImgList;
import com.iqb.constants.RouteActivityURL;
import com.iqb.player.R;
import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.plugin.contract.IQBLivePluginContract;
import com.iqb.player.mvp.plugin.view.LivePluginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class LivePluginPresenter extends IQBLivePluginContract.IQBLivePluginContractPresenter<LivePluginView> {

    private String url;

    public LivePluginPresenter() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected IBaseModel bindModel() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerDrawPPT(LiveForPlayerDrawPPT liveForPlayerDrawPPT) {
        switch (liveForPlayerDrawPPT.getPptDraw()) {
            case "pptDrawCtlTypeBegin":
                getView().pptDrawCtlTypeBegin(liveForPlayerDrawPPT.getX(), liveForPlayerDrawPPT.getY());
                break;
            case "pptDrawCtlTypeMove":
                getView().pptDrawCtlTypeMove(liveForPlayerDrawPPT.getX(), liveForPlayerDrawPPT.getY());
                break;
            case "pptDrawCtlTypeEnd":
                getView().pptDrawCtlTypeEnd(liveForPlayerDrawPPT.getX(), liveForPlayerDrawPPT.getY());
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveViewOpenPrepareImg(LiveViewOpenPrepareImg liveViewOpenPrepareImg) {
        getView().setVisibility(View.VISIBLE);
        getView().openImg();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerPPTPage(LiveForPlayerPPTPage liveForPlayerPPTPage) {
        switch (liveForPlayerPPTPage.getIsPageModel()) {
            case 0:
                nextAction();
                break;
            case 1:
                prevAction();
                break;
            case 2:
                playAction();
                break;
            case 3:
                pptSync();
                break;
        }
    }

    private void pptSync() {
        getView().drawCtlTypeClear();
        getView().getWebView().loadUrl(url);
    }

    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void playAction() {
        getView().drawCtlTypeClear();
        getView().getWebView().evaluateJavascript("javascript:var buttons = document.getElementsByClassName(\"play\");" +
                "    {var event = document.createEvent(\"Event\");" +
                "    event.initEvent(\"touchstart\", false, true);" +
                "    buttons[0].dispatchEvent(event);" +
                "    event.initEvent(\"touchend\", false, true);" +
                "    buttons[0].dispatchEvent(event);}", value -> {
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prevAction() {
        getView().drawCtlTypeClear();
        getView().getWebView().evaluateJavascript("javascript:var buttons = document.getElementsByClassName(\"prev\");" +
                "    {var event = document.createEvent(\"Event\");" +
                "    event.initEvent(\"touchstart\", false, true);" +
                "    buttons[0].dispatchEvent(event);" +
                "    event.initEvent(\"touchend\", false, true);" +
                "    buttons[0].dispatchEvent(event);}", value -> {
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void nextAction() {
        getView().drawCtlTypeClear();
        getView().getWebView().evaluateJavascript("javascript:var buttons = document.getElementsByClassName(\"next\");" +
                "    {var event = document.createEvent(\"Event\");" +
                "    event.initEvent(\"touchstart\", false, true);" +
                "    buttons[0].dispatchEvent(event);" +
                "    event.initEvent(\"touchend\", false, true);" +
                "    buttons[0].dispatchEvent(event);}", value -> {
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerBindImg(LiveForPlayerBindImg liveForPlayerBindImg) {
        if (TextUtils.isEmpty(liveForPlayerBindImg.getPicUrl())) {
            getView().removeImgListAllView();
            return;
        }
        getView().bindImgList(liveForPlayerBindImg.getPicUrl().split(","), liveForPlayerBindImg.getPicUrl());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerDrawImg(LiveForPlayerDrawImg liveForPlayerDrawImg) {
        switch (liveForPlayerDrawImg.getDrawCtlType()) {
            case "drawCtlTypeBegin":
                getView().imgDrawCtlTypeBegin(liveForPlayerDrawImg.getDrawCtlPointX(), liveForPlayerDrawImg.getDrawCtlPointY());
                break;
            case "drawCtlTypeMove":
                getView().imgDrawCtlTypeMove(liveForPlayerDrawImg.getDrawCtlPointX(), liveForPlayerDrawImg.getDrawCtlPointY());
                break;
            case "drawCtlTypeEnd":
                getView().imgDrawCtlTypeEnd(liveForPlayerDrawImg.getDrawCtlPointX(), liveForPlayerDrawImg.getDrawCtlPointY());
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerShowPPT(LiveForPlayerShowPPT liveForPlayerShowPPT) {
        if (liveForPlayerShowPPT.getPptShowHide().contains("yes")) {
            url = liveForPlayerShowPPT.getUrl();
            getView().showPPT(liveForPlayerShowPPT.getUrl());
        }else {
            url = liveForPlayerShowPPT.getUrl();
            getView().hidePPT(liveForPlayerShowPPT.getUrl());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerIsPen(LiveForPlayerIsPen liveForPlayerIsPen) {
        getView().isPen(liveForPlayerIsPen.isPen());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerDrawClear(LiveForPlayerDrawClear liveForPlayerDrawClear) {
        getView().drawCtlTypeClear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerShowImg(LiveForPlayerShowImg liveForPlayerShowImg) {
        getView().drawCtlImageOpen(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerChangeImg(LiveForPlayerChangeImg liveForPlayerChangeImg) {
        getView().drawCtlImageChange(liveForPlayerChangeImg.getScoreImageURL());
    }

    @Override
    public void visiblePreImgList(Boolean tag) {
        if (tag) {
            EventBus.getDefault().post(new LiveViewVisibleImgList());
            getView().setImgListVisible(true);
        } else {
            EventBus.getDefault().post(new LiveViewGoneImgList());
            getView().setImgListVisible(false);
        }
    }

    @Override
    public void returnVideo() {
        getView().setVisibility(View.GONE);
        EventBus.getDefault().post(new LiveViewReturnVideo());
    }

    @Override
    public void removeImg(String replace) {
        int num = 0;
        String[] split = replace.split(",");
        for (String s : split) {
            if (!android.text.TextUtils.isEmpty(s)) {
                num++;
            }
        }
        if (num < 1) {
            ToastUtils.showShort("最少保留一张课前准备哦！");
            return;
        }
        if (getView().isDraw(replace)) {
            ToastUtils.showShort("教师批注过图片不可删除！");
            return;
        }
        EventBus.getDefault().post(new LiveViewRemoveImg(replace));
    }

    @Override
    public void intentPrepare() {
        ARouter.getInstance().build(RouteActivityURL.IQB_STUDENT_HOME_PREPARE_ACT).withBoolean(getView().getContext().getString(R.string.home_intent_prepare_key), true).navigation();
    }
}
