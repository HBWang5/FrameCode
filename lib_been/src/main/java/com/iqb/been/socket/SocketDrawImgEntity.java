package com.iqb.been.socket;

import com.iqb.been.base.BaseSocketEntity;
import com.iqb.been.event.PreImgDrawTeacherEvent;

public class SocketDrawImgEntity extends BaseSocketEntity {
    private PreImgDrawTeacherEvent msgContent;

    public PreImgDrawTeacherEvent getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(PreImgDrawTeacherEvent msgContent) {
        this.msgContent = msgContent;
    }
}
