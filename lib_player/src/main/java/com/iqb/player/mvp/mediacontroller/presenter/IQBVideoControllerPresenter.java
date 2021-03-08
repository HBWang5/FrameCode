package com.iqb.player.mvp.mediacontroller.presenter;

import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.mediacontroller.contract.IQBVideoControllerContract;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;

public class IQBVideoControllerPresenter extends IQBVideoControllerContract.IQVideoControllerContractPresenter<IQBVideoControllerView>{
    @Override
    protected IBaseModel bindModel() {
        return null;
    }
}
