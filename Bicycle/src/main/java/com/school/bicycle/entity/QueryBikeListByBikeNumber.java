package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class QueryBikeListByBikeNumber {

    /**
     * code : 1
     * msg : 操作成功
     * bike_info : {"log":"121.450685","lat":"37.486472","number":"011130","type":"1","address":"山东省烟台市莱山区黄海路街道清泉路山东工商学院","long_lease":0,"valid_time":[],"color":"green","lease_info":{"日租":"2","日租":"0.1","月租":"100","季租":"180","半年租":"300","year":"600"}}
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
         * type : 1
         * address : 山东省烟台市莱山区黄海路街道清泉路山东工商学院
         * long_lease : 0
         * valid_time : []
         * color : green
         * lease_info : {"日租":"2","日租":"0.1","月租":"100","季租":"180","半年租":"300","year":"600"}
         */

        private double log;
        private double lat;
        private String number;
        private String type;
        private String address;
        private int long_lease;
        private String color;
        private LeaseInfoBean lease_info;
        private List<?> valid_time;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public List<?> getValid_time() {
            return valid_time;
        }

        public void setValid_time(List<?> valid_time) {
            this.valid_time = valid_time;
        }

        public static class LeaseInfoBean {
            /**
             * 日租 : 2
             * 日租 : 0.1
             * 月租 : 100
             * 季租 : 180
             * 半年租 : 300
             * year : 600
             */

            private String 时租;
            private String 日租;
            private String 月租;
            private String 季租;
            private String 半年租;
            private String year;

            public String get日租() {
                return 日租;
            }

            public void set日租(String 日租) {
                this.日租 = 日租;
            }

            public String get时租() {
                return 日租;
            }

            public void set时租(String 时租) {
                this.日租 = 时租;
            }

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

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }
        }
    }
}
