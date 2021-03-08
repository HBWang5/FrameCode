package com.iqb.constants;

public interface SocketEventConfig {
    /**
     * 学生:
     */
    String CLIENT_REPORT = "client_report";
    /**
     * 老师应答
     */
    String SYSTEM_PUSH = "system_push";
    /**
     * 申请进入教室
     * 学生
     */
    String JOIN_CLASS_MSG_TYPE_STUDENT = "joinChannel";

    /**
     * 申请进入教室
     * 老师应答
     */
    String JOIN_CLASS_MSG_TYPE_TEACHER = "agreeJoin";

    /**
     * live_hand_up_pro_icon
     * 学生
     */
    String HANDS_UP_MSG_TYPE_STUDENT = "raiseHand";

    /**
     * live_hand_up_pro_icon
     * 老师应答
     */
    String HANDS_UP_MSG_TYPE_TEACHER = "raiseHand";
    /**
     * 老师离开教室结束课程
     * 老师应答
     */
    String LEAVE_CHANNEL_MSG_TYPE_TEACHER = "leaveChannel";
    /**
     * 老师离开教室结束课程
     * 系统
     */
    String NOTIFY_OFFLINE_MSG_TYPE_SYSTEM = "notifyOffline";

    /**
     * 老师异常离线
     * 系统
     */
    String TEACHER_OFFLINE_MSG_TYPE_SYSTEM = "teacherOffline";

    /**
     * 客户端检查服务器连接状态
     * 老师
     */
    String CHECK_CONNECT_MSG_TYPE_STUDENT = "checkConn";

    /**
     * 系统应答
     */
    String CHECK_CONNECT_MSG_TYPE_SYSTEM = "checkConn";

    /**
     * 定时心跳
     * 老师
     */
    String HEARTBEAT_MSG_TYPE_SYSTEM = "heartbeat";

    /**
     * 课程状态更新
     * 系统
     */
    String REFRESH_CACHE_MSG_TYPE_SYSTEM = "refreshCache";

    /**
     * PPT播放/声音控制
     */
    String PPT_MSG_TYPE_SYSTEM = "commonCtl";

    /**
     * 老师批注
     */
    String IMG_DRAW_SYSTEM = "drawCtl";
}
