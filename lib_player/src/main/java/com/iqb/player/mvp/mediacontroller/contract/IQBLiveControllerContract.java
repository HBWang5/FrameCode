package com.iqb.player.mvp.mediacontroller.contract;


import android.content.Context;

import com.iqb.player.adapter.LiveContentMsgAdapter;
import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.mediacontroller.ControllerCallBack;
import com.iqb.player.mvp.mediacontroller.IBaseIQBController;
import com.iqb.player.mvp.mediagroup.view.IQBLiveGroup;
import com.iqb.player.mvp.player.IIQBLivePlayer;
import com.iqb.player.mvp.plugin.view.LivePluginView;
import com.iqb.src.widget.IQBPlayerRecyclerView;

public interface IQBLiveControllerContract {


    abstract class IQBLiveControllerRelativeView<P extends IQBLiveControllerContractLivePresenter> extends IBaseContract.BaseRelativeLayoutLiveView<P> implements IBaseIQBController {

        public IQBLiveControllerRelativeView(Context context) {
            super(context);
        }


        public abstract void setPlayerVoiceModel(boolean voiceCtl);

        public abstract void setPlayerRaiseHand();

        public abstract void notifyMsg(int lastItemPosition);

        public abstract void addMsg(String s);

    }

    abstract class IQBLiveControllerContractLivePresenter<V extends IQBLiveControllerRelativeView> extends IBaseContract.BaseLivePresenter<V> {


        public abstract void checkScore();

        public abstract void handsUp();

        public abstract void openCloseLoudspeaker(Boolean tag);

        public abstract void openCloseMicrophone(Boolean tag);

        public abstract void leaveChannel();


        public abstract void notifyMsg(int lastItemPosition);
    }


}
