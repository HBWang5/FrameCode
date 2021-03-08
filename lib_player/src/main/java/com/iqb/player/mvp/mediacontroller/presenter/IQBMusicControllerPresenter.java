package com.iqb.player.mvp.mediacontroller.presenter;

import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.mediacontroller.contract.IQBMusicControllerContract;
import com.iqb.player.mvp.mediacontroller.view.IQBMusicControllerView;

public class IQBMusicControllerPresenter extends IQBMusicControllerContract.IQBMusicControllerContractPresenter<IQBMusicControllerView>{
    @Override
    protected IBaseModel bindModel() {
        return null;
    }
}
