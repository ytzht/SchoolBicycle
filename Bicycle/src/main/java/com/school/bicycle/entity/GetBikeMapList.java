package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class GetBikeMapList {


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"mybike":0,"gd_id":13,"log":"121.450111","lat":"37.486985","number":"011151","distance":"147","address":"山东省烟台市莱山区黄海路街道莱山区黄海路街道工商学院工作站山东工商学院","long_lease":0,"valid_time":[],"color":"green","type":"单人车","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"}},{"mybike":0,"gd_id":14,"log":"121.450397","lat":"37.486531","number":"011154","distance":"202","address":"山东省烟台市莱山区黄海路街道清泉路山东工商学院","long_lease":0,"valid_time":[],"color":"green","type":"单人车","lease_info":{"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"}},{"mybike":0,"gd_id":4,"log":"121.450685","lat":"37.486472","number":"011130","distance":"223","address":"山东省烟台市莱山区黄海路街道清泉路山东工商学院","long_lease":1,"valid_time":["2017-06-25"],"color":"yellow","type":"单人车","lease_info":{"时租":"2","日租":"30"}}]
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
         * gd_id : 13
         * log : 121.450111
         * lat : 37.486985
         * number : 011151
         * distance : 147
         * address : 山东省烟台市莱山区黄海路街道莱山区黄海路街道工商学院工作站山东工商学院
         * long_lease : 0
         * valid_time : []
         * color : green
         * type : 单人车
         * lease_info : {"时租":"2","日租":"30","月租":"100","季租":"180","半年租":"300","年租":"500"}
         */

        private int mybike;
        private int gd_id;
        private double log;
        private double lat;
        private String number;
        private String distance;
        private String address;
        private int long_lease;
        private String color;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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
