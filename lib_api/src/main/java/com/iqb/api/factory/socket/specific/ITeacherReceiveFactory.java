package com.iqb.api.factory.socket.specific;


import com.iqb.api.factory.IBaseFactory;
import com.iqb.api.factory.socket.product.IBaseProduct;

public interface ITeacherReceiveFactory extends IBaseFactory {
    IBaseProduct receiveMsg(String type, String data);
}
