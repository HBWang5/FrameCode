package com.iqb.api.factory.socket.specific;


import com.iqb.api.factory.socket.product.IBaseProduct;
import com.iqb.api.factory.socket.product.JoinStudentProduct;

import static com.iqb.constants.SocketEventConfig.JOIN_CLASS_MSG_TYPE_TEACHER;

public class TeacherSendFactory implements ITeacherSendFactory {
    @Override
    public IBaseProduct sendMsg(String type) {
        switch (type) {
            case JOIN_CLASS_MSG_TYPE_TEACHER:
                return new JoinStudentProduct();
        }
        return null;
    }



}
