package com.iqb.been.home;


import com.iqb.been.base.BaseEntity;

public class HomeUpApkEntity extends BaseEntity {


    /**
     * e : null
     * d : {"name":"studentAM","version":"v1.0.1","url":"http://192.168.8.8/iqb-files/studentAM/iqb_v1.0.0.0_20200730_violation_website_release.apk"}
     */

    private Object e;
    private DBean d;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {
        /**
         * name : studentAM
         * version : v1.0.1
         * url : http://192.168.8.8/iqb-files/studentAM/iqb_v1.0.0.0_20200730_violation_website_release.apk
         */

        private String name;
        private String version;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
