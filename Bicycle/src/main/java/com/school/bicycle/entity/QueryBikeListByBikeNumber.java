package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class QueryBikeListByBikeNumber {


    /**
     * code : 1
     * msg : 操作成功
     * bike_info : {"log":"121.450685","lat":"37.486472","number":"011130","type":"单人车","long_lease":1,"valid_time":["2017-06-25"],"color":"yellow","lease_info":{"时租":"2","日租":"30"}}
     */

    private int code;
    private String msg;
    private BikeInfoBean bike_info;

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

    public BikeInfoBean getBike_info() {
        return bike_info;
    }

    public void setBike_info(BikeInfoBean bike_info) {
        this.bike_info = bike_info;
    }

    public static class BikeInfoBean {
        /**
         * log : 121.450685
         * lat : 37.486472
         * number : 011130
         * type : 单人车
         * long_lease : 1
         * valid_time : ["2017-06-25"]
         * color : yellow
         * lease_info : {"时租":"2","日租":"30"}
         */

        private double log;
        private double lat;
        private String number;
        private String type;
        private int long_lease;
        private String color;
        private LeaseInfoBean lease_info;
        private List<String> valid_time;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
             * 时租 : 2
             * 日租 : 30
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
