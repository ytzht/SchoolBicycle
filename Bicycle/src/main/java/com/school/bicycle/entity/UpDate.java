package com.school.bicycle.entity;

/**
 * Created by ytzht on 2017/07/28 下午9:22
 */

public class UpDate {

    /**
     * code : 1
     * msg : 最新版本
     * body : {"version":"2.0.1","build":11,"content":"1.收货时间优化\t\t","file_url":"/upload/app/xiaov_11.apk","create_time":"2017-05-12 20:21:25"}
     */

    private int code;
    private String msg;
    private BodyBean body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * version : 2.0.1
         * build : 11
         * content : 1.收货时间优化
         * file_url : /upload/app/xiaov_11.apk
         * create_time : 2017-05-12 20:21:25
         */

        private String version;
        private int build;
        private String content;
        private String file_url;
        private String create_time;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getBuild() {
            return build;
        }

        public void setBuild(int build) {
            this.build = build;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
