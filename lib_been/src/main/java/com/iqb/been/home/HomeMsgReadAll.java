package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

public class HomeMsgReadAll extends BaseEntity {


    private String id;
    private int contentType;
    private boolean v3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public boolean isV3() {
        return v3;
    }

    public void setV3(boolean v3) {
        this.v3 = v3;
    }
}
