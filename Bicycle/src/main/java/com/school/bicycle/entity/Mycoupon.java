package com.school.bicycle.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */

public class Mycoupon implements Serializable{


    /**
     * code : 1
     * msg : 操作成功
     * body : [{"usercou_id":9,"user_id":60,"cou_type":"满减","cou_usedin":"时租","cou_full":20,"cou_cut":2,"start_time":"2017-08-01","end_time":"2017-08-31","cou_number":1,"cou_code":"123","used":0,"create_time":"2017-08-05 09:15:48"},{"usercou_id":10,"user_id":60,"cou_type":"折扣","cou_usedin":"无","cou_discount":0.5,"start_time":"2017-08-01","end_time":"2017-08-31","cou_number":1,"cou_code":"lec","used":0,"create_time":"2017-08-05 09:15:58"},{"usercou_id":11,"user_id":60,"cou_type":"满减","cou_usedin":"日租","cou_full":20,"cou_cut":3,"start_time":"2017-08-01","end_time":"2017-08-31","cou_number":1,"cou_code":"abc","used":0,"create_time":"2017-08-05 09:16:34"}]
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

    public static class BodyBean implements Serializable{
        /**
         * usercou_id : 9
         * user_id : 60
         * cou_type : 满减
         * cou_usedin : 时租
         * cou_full : 20.0
         * cou_cut : 2.0
         * start_time : 2017-08-01
         * end_time : 2017-08-31
         * cou_number : 1
         * cou_code : 123
         * used : 0
         * create_time : 2017-08-05 09:15:48
         * cou_discount : 0.5
         */

        private int usercou_id;
        private int user_id;
        private String cou_type;
        private String cou_usedin;
        private double cou_full;
        private double cou_cut;
        private String start_time;
        private String end_time;
        private int cou_number;
        private String cou_code;
        private int used;
        private String create_time;
        private double cou_discount;

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

        public double getCou_full() {
            return cou_full;
        }

        public void setCou_full(double cou_full) {
            this.cou_full = cou_full;
        }

        public double getCou_cut() {
            return cou_cut;
        }

        public void setCou_cut(double cou_cut) {
            this.cou_cut = cou_cut;
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

        public double getCou_discount() {
            return cou_discount;
        }

        public void setCou_discount(double cou_discount) {
            this.cou_discount = cou_discount;
        }
    }
}
