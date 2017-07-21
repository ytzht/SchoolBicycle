package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class BikeAlarmList {

    /**
     * code : 1
     * msg : 操作成功
     * alarm_list : [{"aid":2,"user_id":0,"bike_number":"011124","bike_type":"单人车","log":"111.111","lat":"78.22","address":"address","create_time":"2017-07-20 18:05:39"}]
     */

    private int code;
    private String msg;
    private List<AlarmListBean> alarm_list;

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

    public List<AlarmListBean> getAlarm_list() {
        return alarm_list;
    }

    public void setAlarm_list(List<AlarmListBean> alarm_list) {
        this.alarm_list = alarm_list;
    }

    public static class AlarmListBean {
        /**
         * aid : 2
         * user_id : 0
         * bike_number : 011124
         * bike_type : 单人车
         * log : 111.111
         * lat : 78.22
         * address : address
         * create_time : 2017-07-20 18:05:39
         */

        private int aid;
        private int user_id;
        private String bike_number;
        private String bike_type;
        private double log;
        private double lat;
        private String address;
        private String create_time;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getBike_number() {
            return bike_number;
        }

        public void setBike_number(String bike_number) {
            this.bike_number = bike_number;
        }

        public String getBike_type() {
            return bike_type;
        }

        public void setBike_type(String bike_type) {
            this.bike_type = bike_type;
        }

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
