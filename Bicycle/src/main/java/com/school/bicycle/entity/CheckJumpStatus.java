package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class CheckJumpStatus {


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"mybike":1,"gd_id":113,"log":"121.441261","lat":"37.477207","number":"011169","address":"山东省烟台市莱山区黄海路街道观海路191号山东工商学院(桐林路)","color":"blue","long_lease":0,"valid_time":[],"type":"1","lease_info":{"时租":"0.04","日租":"0.02"}}]
     * bike_status : 1
     * bike_number : 011169
     * lock_status : 1
     */

    private int code;
    private String msg;
    private int bike_status;
    private String bike_number;
    private int lock_status;
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

    public int getBike_status() {
        return bike_status;
    }

    public void setBike_status(int bike_status) {
        this.bike_status = bike_status;
    }

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public int getLock_status() {
        return lock_status;
    }

    public void setLock_status(int lock_status) {
        this.lock_status = lock_status;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * mybike : 1
         * gd_id : 113
         * log : 121.441261
         * lat : 37.477207
         * number : 011169
         * address : 山东省烟台市莱山区黄海路街道观海路191号山东工商学院(桐林路)
         * color : blue
         * long_lease : 0
         * valid_time : []
         * type : 1
         * lease_info : {"时租":"0.04","日租":"0.02"}
         */

        private int mybike;
        private int gd_id;
        private double log;
        private double lat;
        private String number;
        private String address;
        private String color;
        private int long_lease;
        private String type;
        private LeaseInfoBean lease_info;
        private List<?> valid_time;

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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLong_lease() {
            return long_lease;
        }

        public void setLong_lease(int long_lease) {
            this.long_lease = long_lease;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public LeaseInfoBean getLease_info() {
            return lease_info;
        }

        public void setLease_info(LeaseInfoBean lease_info) {
            this.lease_info = lease_info;
        }

        public List<?> getValid_time() {
            return valid_time;
        }

        public void setValid_time(List<?> valid_time) {
            this.valid_time = valid_time;
        }

        public static class LeaseInfoBean {
            /**
             * 时租 : 0.04
             * 日租 : 0.02
             */

            private String 时租;
            private String 日租;

            public String get时租() {
                return 时租;
            }

            public void set时租(String 时租) {
                this.时租 = 时租;
            }

            public String get日租() {
                return 日租;
            }

            public void set日租(String 日租) {
                this.日租 = 日租;
            }
        }
    }
}
