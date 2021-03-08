package com.iqb.been.event;

public class PreImgDrawTeacherEvent {
    private String drawCtlType;
    private String scoreImageURL;
    private float drawCtlPointX;
    private float drawCtlPointY;
    private boolean drawCtlErase;
    private int drawCtlMode;

    public String getDrawCtlType() {
        return drawCtlType;
    }

    public void setDrawCtlType(String drawCtlType) {
        this.drawCtlType = drawCtlType;
    }

    public String getScoreImageURL() {
        return scoreImageURL;
    }

    public void setScoreImageURL(String scoreImageURL) {
        this.scoreImageURL = scoreImageURL;
    }

    public float getDrawCtlPointX() {
        return drawCtlPointX;
    }

    public void setDrawCtlPointX(float drawCtlPointX) {
        this.drawCtlPointX = drawCtlPointX;
    }

    public float getDrawCtlPointY() {
        return drawCtlPointY;
    }

    public void setDrawCtlPointY(float drawCtlPointY) {
        this.drawCtlPointY = drawCtlPointY;
    }

    public boolean getDrawCtlErase() {
        return drawCtlErase;
    }

    public void setDrawCtlErase(boolean drawCtlErase) {
        this.drawCtlErase = drawCtlErase;
    }

    public int getDrawCtlMode() {
        return drawCtlMode;
    }

    public void setDrawCtlMode(int drawCtlMode) {
        this.drawCtlMode = drawCtlMode;
    }

}
