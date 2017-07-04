package com.school.bicycle.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class GetBikeMapList {

    /**
     * code : 1
     * msg : 操作成功
     * body : [{"mybike":0,"gd_id":14,"log":"121.450397","lat":"37.486531","number":"011154","distance":"17","long_lease":0,"valid_time":["2017-06-25 17:22:15.0","2017-06-26 17:22:30.0","2017-06-25 17:23:14.0","2017-06-25 17:23:38.0"],"color":"yellow","lease_info":{"hour":"2元每小时","day":"30元每天","long":"100元每个月;"}}]
     */

    private int code;
    private String msg;
    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * mybike : 0
         * gd_id : 14
         * log : 121.450397
         * lat : 37.486531
         * number : 011154
         * distance : 17
         * long_lease : 0
         * valid_time : ["2017-06-25 17:22:15.0","2017-06-26 17:22:30.0","2017-06-25 17:23:14.0","2017-06-25 17:23:38.0"]
         * color : yellow
         * lease_info : {"hour":"2元每小时","day":"30元每天","long":"100元每个月;"}
         */

        private int mybike;
        private int gd_id;
        private String log;
        private String lat;
        private String number;
        private String distance;
        private int long_lease;
        private String color;
        private LeaseInfoBean lease_info;
        private List<String> valid_time;

        public int getMybike() {
            return mybike;
        }

        public void setMybike(int mybike) {
            this.mybike = mybike;
        }

        public int getGd_id() {
            return gd_id;
        }

        public void setGd_id(int gd_id) {
            this.gd_id = gd_id;
        }

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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getLong_lease() {
            return long_lease;
        }

        public void setLong_lease(int long_lease) {
            this.long_lease = long_lease;
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

        public List<String> getValid_time() {
            return valid_time;
        }

        public void setValid_time(List<String> valid_time) {
            this.valid_time = valid_time;
        }

        public static class LeaseInfoBean {
            /**
             * hour : 2元每小时
             * day : 30元每天
             * long : 100元每个月;
             */

            private String hour;
            private String day;
            @SerializedName("long")
            private String longX;

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

            public String getLongX() {
                return longX;
            }

            public void setLongX(String longX) {
                this.longX = longX;
            }
        }
    }
}
