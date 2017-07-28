package com.school.bicycle.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class QueryBikeListByDate implements Serializable{


    /**
     * code : 1
     * msg : 操作成功
     * bike_info : [{"log":"121.450685","lat":"37.486472","number":"011001","type":"1","color":"green","valid_time":[]}]
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

    public static class BikeInfoBean implements Serializable {
        /**
         * log : 121.450685
         * lat : 37.486472
         * number : 011001
         * type : 1
         * color : green
         * valid_time : []
         */

        private double log;
        private double lat;
        private String number;
        private String type;
        private String color;
        private String address;
        private List<?> valid_time;
        private LeaseInfoBean lease_info;

        public String getAdress() {
            return address;
        }

        public void setAdress(String adress) {
            this.address = adress;
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

        public List<?> getValid_time() {
            return valid_time;
        }

        public void setValid_time(List<?> valid_time) {
            this.valid_time = valid_time;
        }

        public LeaseInfoBean getLease_info() {
            return lease_info;
        }

        public void setLease_info(LeaseInfoBean lease_info) {
            this.lease_info = lease_info;
        }

        public static class LeaseInfoBean implements Serializable{
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
