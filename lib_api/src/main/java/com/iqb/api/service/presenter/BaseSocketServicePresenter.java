package com.iqb.api.service.presenter;


import android.content.Context;

import com.iqb.api.base.baseservice.presenter.BaseServicePresenter;
import com.iqb.api.factory.socket.impl.SocketTypeFactory;
import com.iqb.api.factory.socket.product.IBaseProduct;
import com.iqb.api.factory.socket.specific.ITeacherSendFactory;
import com.iqb.api.service.model.BaseSocketServiceModel;
import com.iqb.api.service.view.BaseSocketService;
import com.iqb.been.event.JoinClassTeacherEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.iqb.constants.SocketEventConfig.JOIN_CLASS_MSG_TYPE_TEACHER;


public class BaseSocketServicePresenter extends BaseServicePresenter<BaseSocketServiceModel, BaseSocketService> implements IBaseSocketServicePresenter {

    public BaseSocketServicePresenter(Context context) {
        super(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public BaseSocketServiceModel bindModel() {
        return new BaseSocketServiceModel(getContext());
    }

    @Override
    public void initConfig(String connectQuery) {
        getModel().initConfig(connectQuery);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void joinChannle(JoinClassTeacherEvent joinClassStudentEvent) {
        ITeacherSendFactory teacherSendFactory = SocketTypeFactory.getInstance().createTeacherSendFactory();
        IBaseProduct iBaseProduct = teacherSendFactory.sendMsg(JOIN_CLASS_MSG_TYPE_TEACHER);
        iBaseProduct.initConfig(joinClassStudentEvent.getLiveId());
        iBaseProduct.execute();
    }


    @Override
    public void connect() {
        getModel().connect();
    }

    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
    }
}
