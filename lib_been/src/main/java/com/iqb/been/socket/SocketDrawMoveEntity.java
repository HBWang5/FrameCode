package com.iqb.been.socket;


public class SocketDrawMoveEntity {


    /**
     * drawCtlErase : false
     * drawCtlPointY : 0.48550724317185623
     * scoreImageURL : /org-files/livaAttachment/20201012/1602472699276099128.jpg
     * drawCtlPointX : 0.4468599033816425
     * drawCtlType : drawCtlTypeMove
     */

    private boolean drawCtlErase;
    private double drawCtlPointY;
    private String scoreImageURL;
    private double drawCtlPointX;
    private String drawCtlType;

    public boolean isDrawCtlErase() {
        return drawCtlErase;
    }

    public void setDrawCtlErase(boolean drawCtlErase) {
        this.drawCtlErase = drawCtlErase;
    }

    public double getDrawCtlPointY() {
        return drawCtlPointY;
    }

    public void setDrawCtlPointY(double drawCtlPointY) {
        this.drawCtlPointY = drawCtlPointY;
    }

    public String getScoreImageURL() {
        return scoreImageURL;
    }

    public void setScoreImageURL(String scoreImageURL) {
        this.scoreImageURL = scoreImageURL;
    }

    public double getDrawCtlPointX() {
        return drawCtlPointX;
    }

    public void setDrawCtlPointX(double drawCtlPointX) {
        this.drawCtlPointX = drawCtlPointX;
    }

    public String getDrawCtlType() {
        return drawCtlType;
    }

    public void setDrawCtlType(String drawCtlType) {
        this.drawCtlType = drawCtlType;
    }
}
