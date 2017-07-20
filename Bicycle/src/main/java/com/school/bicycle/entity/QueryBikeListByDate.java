package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class QueryBikeListByDate {


    /**
     * code : 1
     * msg : 操作成功
     * bike_info : [{"log":"121.450111","lat":"37.486985","number":"011151","type":"单人车","color":"green","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"},"valid_time":"ALL"},{"log":"121.450397","lat":"37.486531","number":"011154","type":"单人车","color":"green","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"},"valid_time":"ALL"},{"log":"121.442166","lat":"37.477763","number":"011140","type":"单人车","color":"green","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"},"valid_time":"ALL"},{"log":"121.442166","lat":"37.477763","number":"01184","type":"单人车","color":"green","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"},"valid_time":"ALL"},{"log":"121.450685","lat":"37.486472","number":"011130","type":"单人车","color":"yellow","valid_time":["2017-06-25"],"lease_info":{"时租":"2","日租":"30"}}]
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
         * log : 121.450111
         * lat : 37.486985
         * number : 011151
         * type : 单人车
         * color : green
         * lease_info : {"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"}
         * valid_time : ALL
         */

        private double log;
        private double lat;
        private String number;
        private String type;
        private String color;
        private LeaseInfoBean lease_info;
        private String valid_time;

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

        public String getValid_time() {
            return valid_time;
        }

        public void setValid_time(String valid_time) {
            this.valid_time = valid_time;
        }

        public static class LeaseInfoBean {
            /**
             * 时租 : 2
             * 日租 : 30
             * 月租 : 100
             * 季租 : 180
             * 半年租 : 300
             * 年租 : 500
             */

            private String 时租;
            private String 日租;
            private String 月租;
            private String 季租;
            private String 半年租;
            private String 年租;

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
}
