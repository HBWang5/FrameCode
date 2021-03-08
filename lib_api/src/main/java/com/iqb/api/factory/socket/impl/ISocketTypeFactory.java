package com.iqb.api.factory.socket.impl;


import com.iqb.api.factory.socket.specific.ITeacherReceiveFactory;
import com.iqb.api.factory.socket.specific.ITeacherSendFactory;

public interface ISocketTypeFactory {

    void switchSocketTypeFactory(String eventType);

    ITeacherReceiveFactory createTeacherReceiveFactory();

    ITeacherSendFactory createTeacherSendFactory();

}
