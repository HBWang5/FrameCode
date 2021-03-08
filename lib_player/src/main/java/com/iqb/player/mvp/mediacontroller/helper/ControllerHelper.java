package com.iqb.player.mvp.mediacontroller.helper;



public class ControllerHelper {
    private static ControllerHelper controllerHelper;

    private ControllerHelper() {
    }

    public static synchronized ControllerHelper getInstance() {
        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }
        return controllerHelper;
    }

}
