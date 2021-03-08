package com.iqb.constants;

public interface HttpUrl {
    /**
     * 登录
     */
    String TEACHER_LOGIN = "/org/api/token/teacher";

    /**
     * 获取短信
     */
    String GET_NOTE = "/org/api/sms/code";

    /**
     * 验证短信
     */
    String VERIFY_NOTE = "/org/api/sms/verify";
    /**
     * 重置密码
     */
    String RESET_PASSWORD = "/org/api/teacher/{pwd}/reset";
    /**
     * 网络测速
     */
    String NET_VELOCITY = "/iqb-files/speedtest.zip";
    /**
     * 重置手机号
     */
    String RESET_PHONE = "/org/api/teacher/mobile/{mobile}/reset";
    /**
     * 重置手机号
     */
    String RETURN_MSG = "/org/api/teacher/v2/suggest";

    /**
     * 设置或更新教师单节课时长度
     */
    String COURSE_LEN = "/org/api/teacher/updateCourseLen/{courseLen}";
    /**
     * 忘记密码
     */
    String FORGET_PASSWORD = "/org/api/teacher/pwd/forgot";

    /**
     * 分页获取教师课程历史或统计明细记录列表
     */
    String TEACHER_RECORDS = "/org/api/v3/k12/teacher/getLiveRecords";

    /**
     * 教师直播时长统计分析（总计、月、日）
     */
    String TEACHER_LIVE_TIME = "/org/api/v3/k12/teacher/getStatisticsInfo";

    /**
     * 获取周列表
     */
    String TEACHER_GET_WEEK_LIST = "/org/api/v3/k12/teacher/weekList";

    /**
     * 老师获取课表
     */
    String TEACHER_GET_CLASS_LIST = "/org/api/administrator/teacherGetSchedule";

    /**
     * 教师端：获取指定学生的课程记录信息(仅仅跟某老师有关的)
     */
    String GET_EVALUATE_LIST_DATA = "/org/api/v3/k12/getStuRecordInfoAllByTeacher/{pageIndex}/{pageSize}";

    /**
     * 教师端：获取我的历史学生
     */
    String GET_STUDENT_LIST = "/org/api/v3/k12/teacher/roomInfo";

    /**
     * 教师端：移除学生（历史学生）
     */
    String DELETE_STUDENT = "/org/api/v3/k12/teacher/historyStudent/remove";

    /**
     * 教师端：获取个人信息
     */
    String GET_TEACHER_DATA = "/org/api/teacher/info";

    /**
     * 完善或更新个人资料
     */
    String SET_TEACHER_DATA = "/org/api/teacher/info/update";

    /**
     * 上传头像
     */
    String UP_ICON = "/org/api/teacher/upload/ico";

    /**
     * 获取当前登录用户历史消息，根据时间戳到当前这段时间推送内容
     */
    String GET_MSG_LIST = "/org/api/push/current/{timestamp}";

    /**
     * 标记通知为已读状态
     */
    String READ_MSG = "/org/api/push/mark/read";

    /**
     * 批量标记多条消息通知为已读状态
     */
    String READ_MSG_ALL = "/org/api/push/mark/readAll";
    /**
     * 教师端：获取房间号和密码
     */
    String GET_ROOM_PWD = "/org/api/v3/k12/teacher/getRoomPwd";

    /**
     * 教师端：反馈已读
     */
    String READ_FEEDBACK = "/org/api/v3/k12/student/readFeedback/{id}";

    /**
     * 教师端：针对某学生家长的全部反馈，一次性设置已读
     */
    String READ_FEEDBACK_ALL = "/org/api/v3/k12/student/readAllFeedback";

    /**
     * 教师端：评价
     */
    String TEACHER_COMMENT = "/org/api/v3/k12/teacher/comment";

    /**
     * 教师端：获取声网token
     */
    String GET_ACCESS_TOKEN = "/org/api/v3/k12/teacher/agora/access/token/{channel}";


    String TEACHER_JOIN_CHANNEL = "/org/api/v3/k12/teacher/joinChannel";
    /**
     * 教师端：老师同意学生进入直播间
     */
    String TEACHER_AGREE_JOIN_CHANNEL = "/org/api/v3/k12/teacher/agree/{studentId}/joinChannel";

    /**
     * 教师端：获取streamKey
     */
    String FET_STREAM_KEY = "/org/api/v3/k12/teacher/getStreamKey";

    /**
     * 教师端：离开小班课教室（下课）
     */
    String LIVE_CHANNEL = "/org/api/v3/k12/teacher/leaveChannel";

    /**
     * 教师获取学生上传的曲谱
     */
    String GET_STUDENT_IMG_LIST = "/org/api/v3/k12/getStuRecordInfo";

    /**
     * 老师获取课程PPT
     */
    String TEACHER_GET_PPT = "/org/api/administrator/teacherGetPPTs";

    /**
     * 教师端：获取正在直播教室学生列表信息
     */
    String GET_ROOM_INFO = "/org/api/v3/k12/teacher/live/roomInfo";

    /**
     * 教师端：将学生踢出直播房间
     */
    String Kill_STUDENT = "/org/api/v3/k12/teacher/channel/kickOutStudent";

    /**
     * 版本升级
     */
    String APP_UP_DATA = "/v2/api/app/getVersion?name=teacherAM";


    /**
     * 设置屏幕模式
     */
    String LIVE_WIN_STATE = "/org/api/teacher/updateSpltScrMode/{spltScrMode}";

    /**
     * 设置或更新教师侧小班课混音控制方式
     */
    String LIVE_SOUND_STATE = "/org/api/teacher/updateAudioMixMode/{audioMixMode}";

    /**
     * 设置或更新教师侧小班课曲谱显示方式
     */
    String LIVE_STAFF_STATE = "/org/api/teacher/updateScoreDispMode/{scoreDispMode}";
}
