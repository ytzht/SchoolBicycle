package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class GetLongLeaseInfo {


    /**
     * code : 1
     * msg : 操作成功
     * longlease_info : [{"月租":"100"},{"季租":"180"},{"半年租":"300"},{"年租":"500"}]
     */

    private int code;
    private String msg;
    private List<LongleaseInfoBean> longlease_info;

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

    public List<LongleaseInfoBean> getLonglease_info() {
        return longlease_info;
    }

    public void setLonglease_info(List<LongleaseInfoBean> longlease_info) {
        this.longlease_info = longlease_info;
    }

    public static class LongleaseInfoBean {
        /**
         * 月租 : 100
         * 季租 : 180
         * 半年租 : 300
         * 年租 : 500
         */

        private String 月租;
        private String 季租;
        private String 半年租;
        private String 年租;

        public String get月租() {
            return 月租;
        }

        public void set月租(String 月租) {
            this.月租 = 月租;
        }

        public String get季租() {
            return 季租;
        }

        public void set季租(String 季租) {
            this.季租 = 季租;
        }

        public String get半年租() {
            return 半年租;
        }

        public void set半年租(String 半年租) {
            this.半年租 = 半年租;
        }

        public String get年租() {
            return 年租;
        }

        public void set年租(String 年租) {
            this.年租 = 年租;
        }
    }
}
