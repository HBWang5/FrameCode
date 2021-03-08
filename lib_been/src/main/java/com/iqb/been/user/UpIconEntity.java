package com.iqb.been.user;


import com.iqb.been.base.HttpResponseBean;

public class UpIconEntity extends HttpResponseBean {

    /**
     * url : /iqb-files/icon/20200710/1594346208447054127.jpg
     * type : .jpg
     * original : head1594346198807.jpg
     * m : 附件上传成功
     * s : true
     */

    private String url;
    private String type;
    private String original;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
