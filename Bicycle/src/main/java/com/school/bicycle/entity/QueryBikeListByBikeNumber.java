package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class QueryBikeListByBikeNumber {

    /**
     * code : 1
     * msg : 操作成功
     * bike_info : [{"log":"121.450685","lat":"37.486472","number":"011130","type":"单人车","start_time":"2017-06-25 00:00:00","color":"yellow","lease_info":{"sz":"2","rz":"30"}},{"log":"121.450685","lat":"37.486472","number":"011130","type":"单人车","start_time":"2017-06-26 00:00:00","color":"yellow","lease_info":{"sz":"2","rz":"30"}}]
     */

    private int code;
    private String msg;
    private List<BikeInfoBean> bike_info;

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

    public List<BikeInfoBean> getBike_info() {
        return bike_info;
    }

    public void setBike_info(List<BikeInfoBean> bike_info) {
        this.bike_info = bike_info;
    }

    public static class BikeInfoBean {

        /**
         * log : 121.450685
         * lat : 37.486472
         * number : 011130
         * type : 单人车
         * start_time : 2017-06-25 00:00:00
         * color : yellow
         * lease_info : {"sz":"2","rz":"30"}
         */

        private String log;
        private String lat;
        private String number;
        private String type;
        private String start_time;
        private String color;
        private LeaseInfoBean lease_info;

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public LeaseInfoBean getLease_info() {
            return lease_info;
        }

        public void setLease_info(LeaseInfoBean lease_info) {
            this.lease_info = lease_info;
        }

        public static class LeaseInfoBean {
            /**
             * sz : 2
             * rz : 30
             */

            private String hour;
            private String day;

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }

}
