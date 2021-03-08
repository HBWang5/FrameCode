package com.iqb.api.factory.socket.specific;


import com.iqb.api.factory.socket.product.IBaseProduct;
import com.iqb.been.event.HandUpStudentEvent;
import com.iqb.been.event.ImgListStudentEvent;
import com.iqb.been.event.JoinClassStudentEvent;
import com.iqb.been.event.OffLineEvent;
import com.iqb.been.socket.SocketIdEntity;
import com.iqb.been.event.LeaveStudentEvent;
import org.greenrobot.eventbus.EventBus;

import io.socket.tools.GsonTools;

import static com.iqb.constants.SocketEventConfig.*;
import static io.socket.client.Socket.EVENT_DISCONNECT;
import static io.socket.client.Socket.EVENT_ERROR;

public class TeacherReceiveFactory implements ITeacherReceiveFactory {
    @Override
    public IBaseProduct receiveMsg(String type, String data) {
        switch (type) {
            case JOIN_CLASS_MSG_TYPE_STUDENT: {
                SocketIdEntity socketEntity = GsonTools.getInstance().fromJson(data, SocketIdEntity.class);
                JoinClassStudentEvent joinClassStudentEvent = GsonTools.getInstance().fromJson(socketEntity.getMsgContent().toString(), JoinClassStudentEvent.class);
                joinClassStudentEvent.setStudentId(socketEntity.getSourceId());
                EventBus.getDefault().post(joinClassStudentEvent);
            }
            break;
            case HANDS_UP_MSG_TYPE_STUDENT: {
                SocketIdEntity socketEntity = GsonTools.getInstance().fromJson(data, SocketIdEntity.class);
                HandUpStudentEvent handUpStudentEvent = GsonTools.getInstance().fromJson(socketEntity.getMsgContent().toString(), HandUpStudentEvent.class);
                EventBus.getDefault().post(handUpStudentEvent);
            }
            break;
            case IMG_DRAW_SYSTEM: {
                SocketIdEntity socketEntity = GsonTools.getInstance().fromJson(data, SocketIdEntity.class);
                ImgListStudentEvent imgListStudentEvent = new ImgListStudentEvent();
                imgListStudentEvent.setSourceId(socketEntity.getSourceId());
                imgListStudentEvent.setTargetId(socketEntity.getTargetId());
                EventBus.getDefault().post(imgListStudentEvent);
            }
            break;
            case LEAVE_CHANNEL_MSG_TYPE_TEACHER: {
                LeaveStudentEvent leaveStudentEvent = GsonTools.getInstance().fromJson(data, LeaveStudentEvent.class);
                EventBus.getDefault().post(leaveStudentEvent);
            }
            break;
            case NOTIFY_OFFLINE_MSG_TYPE_SYSTEM:
            case EVENT_ERROR:
            case EVENT_DISCONNECT: {
                EventBus.getDefault().post(new OffLineEvent());
            }
            break;
        }
        return null;
    }
}
