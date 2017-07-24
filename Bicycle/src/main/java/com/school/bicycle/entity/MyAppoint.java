package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyAppoint {
    /**
     * code : 1
     * msg : 操作成功
     * my_appoint : [{"log":"121.450685","lat":"37.486472","aid":2,"number":"011154","type":"1","appoint_time":"2017-06-25"}]
     */

    private int code;
    private String msg;
    private List<MyAppointBean> my_appoint;

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

    public List<MyAppointBean> getMy_appoint() {
        return my_appoint;
    }

    public void setMy_appoint(List<MyAppointBean> my_appoint) {
        this.my_appoint = my_appoint;
    }

    public static class MyAppointBean {
        /**
         * log : 121.450685
         * lat : 37.486472
         * aid : 2
         * number : 011154
         * type : 1
         * appoint_time : 2017-06-25
         */

        private double log;
        private double lat;
        private int aid;
        private String number;
        private String type;
        private String appoint_time;

        public double getLog() {
            return log;
        }

        public void setLog(double log) {
            this.log = log;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAppoint_time() {
            return appoint_time;
        }

        public void setAppoint_time(String appoint_time) {
            this.appoint_time = appoint_time;
        }
    }
}
