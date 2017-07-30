package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class CheckJumpStatus {

    /**
     * code : 1
     * msg : 操作成功
     * body : [{"mybike":1,"gd_id":67,"log":"121.44145488739015","lat":"37.47678236501855","number":"011130","address":"山东省烟台市莱山区黄海路街道清泉路山东工商学院","color":"blue","long_lease":0,"valid_time":[],"type":"1","lease_info":{"时租":"0.04","日租":"0.02"}}]
     * bike_status : 3
     * my_route : {"rid":44,"user_id":31,"bike_number":"011130","start_time":"2017-07-28 17:00:04","end_time":"2017-07-28 17:04:07","time_span":"4","total_fee":0,"distance":5.79,"calories":2,"status":1,"pay_status":0}
     */

    private int code;
    private String msg;
    private int bike_status;
    private MyRouteBean my_route;
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

    public MyRouteBean getMy_route() {
        return my_route;
    }

    public void setMy_route(MyRouteBean my_route) {
        this.my_route = my_route;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class MyRouteBean {
        /**
         * rid : 44
         * user_id : 31
         * bike_number : 011130
         * start_time : 2017-07-28 17:00:04
         * end_time : 2017-07-28 17:04:07
         * time_span : 4
         * total_fee : 0.0
         * distance : 5.79
         * calories : 2
         * status : 1
         * pay_status : 0
         */

        private int rid;
        private int user_id;
        private String bike_number;
        private String start_time;
        private String end_time;
        private String time_span;
        private double total_fee;
        private double distance;
        private int calories;
        private int status;
        private int pay_status;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTime_span() {
            return time_span;
        }

        public void setTime_span(String time_span) {
            this.time_span = time_span;
        }

        public double getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(double total_fee) {
            this.total_fee = total_fee;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }
    }

    public static class BodyBean {
        /**
         * mybike : 1
         * gd_id : 67
         * log : 121.44145488739015
         * lat : 37.47678236501855
         * number : 011130
         * address : 山东省烟台市莱山区黄海路街道清泉路山东工商学院
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
