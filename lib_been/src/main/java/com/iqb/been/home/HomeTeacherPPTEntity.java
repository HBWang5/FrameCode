package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

import java.util.List;

public class HomeTeacherPPTEntity extends BaseEntity {

    private List<ResourcesBean> resources;

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }

    public static class ResourcesBean {
        /**
         * id : 36ad22007a9c49d282f7354341725524
         * resId : bd0d1f60dd4d4b08b4fed48d4b85dbbd
         * resName : 测试模板横屏
         * teacherId :
         * createdAt : 1566355688138
         * displayUrl : https://www.iqinban.com/ppttest/
         * coverUrl : https://www.iqinban.com/ppttest/cover.png
         */

        private String id;
        private String resId;
        private String resName;
        private String teacherId;
        private String createdAt;
        private String displayUrl;
        private String coverUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResId() {
            return resId;
        }

        public void setResId(String resId) {
            this.resId = resId;
        }

        public String getResName() {
            return resName;
        }

        public void setResName(String resName) {
            this.resName = resName;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public void setDisplayUrl(String displayUrl) {
            this.displayUrl = displayUrl;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }
    }
}
