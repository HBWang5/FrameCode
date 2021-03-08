package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

public class HomeLiveTimeEntity extends BaseEntity {

    /**
     * stuCount : 3
     * lessonCount : 55
     * todayTime : 0
     * monthTime : 18
     * totalTime : 5065
     */

    private int stuCount;
    private int lessonCount;
    private int todayTime;
    private int monthTime;
    private int totalTime;

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(int todayTime) {
        this.todayTime = todayTime;
    }

    public int getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(int monthTime) {
        this.monthTime = monthTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
