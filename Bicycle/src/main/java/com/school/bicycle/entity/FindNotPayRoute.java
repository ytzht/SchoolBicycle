package com.school.bicycle.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class FindNotPayRoute implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * coupon : [{"usercou_id":1,"user_id":48,"cou_type":"折扣","cou_usedin":"无","cou_discount":1,"start_time":"2017-08-01","end_time":"2017-08-31","cou_number":1,"cou_code":"lec","used":0,"create_time":"2017-08-04 14:33:27"},{"usercou_id":1,"user_id":48,"cou_type":"折扣","cou_usedin":"无","cou_discount":1,"start_time":"2017-08-01","end_time":"2017-08-31","cou_number":1,"cou_code":"lec","used":0,"create_time":"2017-08-04 14:33:27"}]
     * time_span : 0
     * distance : 0.00
     * calories : 0
     * total_fee : 0.50
     * bike_number : 011007
     * balance : 1087.50
     */

    private int code;
    private String msg;
    private String time_span;
    private String distance;
    private String calories;
    private String total_fee;
    private String bike_number;
    private String balance;
    private List<CouponBean> coupon;

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

    public String getTime_span() {
        return time_span;
    }

    public void setTime_span(String time_span) {
        this.time_span = time_span;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public static class CouponBean implements Serializable{
        /**
         * usercou_id : 1
         * user_id : 48
         * cou_type : 折扣
         * cou_usedin : 无
         * cou_discount : 1
         * start_time : 2017-08-01
         * end_time : 2017-08-31
         * cou_number : 1
         * cou_code : lec
         * used : 0
         * create_time : 2017-08-04 14:33:27
         */

        private int usercou_id;
        private int user_id;
        private String cou_type;
        private String cou_usedin;
        private double cou_discount;
        private String start_time;
        private String end_time;
        private int cou_number;
        private String cou_code;
        private int used;
        private String create_time;

        public int getUsercou_id() {
            return usercou_id;
        }

        public void setUsercou_id(int usercou_id) {
            this.usercou_id = usercou_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getCou_type() {
            return cou_type;
        }

        public void setCou_type(String cou_type) {
            this.cou_type = cou_type;
        }

        public String getCou_usedin() {
            return cou_usedin;
        }

        public void setCou_usedin(String cou_usedin) {
            this.cou_usedin = cou_usedin;
        }

        public double getCou_discount() {
            return cou_discount;
        }

        public void setCou_discount(double cou_discount) {
            this.cou_discount = cou_discount;
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

        public int getCou_number() {
            return cou_number;
        }

        public void setCou_number(int cou_number) {
            this.cou_number = cou_number;
        }

        public String getCou_code() {
            return cou_code;
        }

        public void setCou_code(String cou_code) {
            this.cou_code = cou_code;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
