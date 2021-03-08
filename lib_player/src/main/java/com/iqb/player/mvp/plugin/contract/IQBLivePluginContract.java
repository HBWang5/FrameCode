package com.iqb.player.mvp.plugin.contract;

import android.content.Context;
import android.webkit.WebView;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.plugin.IPluginUI;

public interface IQBLivePluginContract {
    abstract class IQBLivePluginRelativeView<P extends IQBLivePluginContractPresenter> extends IBaseContract.BaseRelativeLayoutLiveView<P> implements IPluginUI {

        public IQBLivePluginRelativeView(Context context) {
            super(context);
        }

        public abstract void pptDrawCtlTypeBegin(double x, double y);

        public abstract void pptDrawCtlTypeMove(double x, double y);

        public abstract void pptDrawCtlTypeEnd(double x, double y);

        public abstract void showPPT(String url);

        public abstract void isPen(boolean pen);

        public abstract WebView getWebView();

        public abstract void drawCtlTypeClear();

        public abstract void drawCtlImageOpen(boolean b);

        public abstract void drawCtlImageChange(String scoreImageURL);

        public abstract void removeImgListAllView();

        public abstract void bindImgList(String[] split, String picUrl);

        public abstract void imgDrawCtlTypeBegin(float drawCtlPointX, float drawCtlPointY);

        public abstract void imgDrawCtlTypeMove(float drawCtlPointX, float drawCtlPointY);

        public abstract void imgDrawCtlTypeEnd(float drawCtlPointX, float drawCtlPointY);

        public abstract void setImgListVisible(boolean b);

        public abstract boolean isDraw(String replace);

        public abstract void hidePPT(String url);

        public abstract void openImg();

    }

    abstract class IQBLivePluginContractPresenter<V extends IQBLivePluginRelativeView> extends IBaseContract.BaseLivePresenter<V> {
        public abstract void visiblePreImgList(Boolean tag);

        public abstract void returnVideo();

        public abstract void removeImg(String replace);

        public abstract void intentPrepare();

    }

}
