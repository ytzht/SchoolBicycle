package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Consumption {


    /**
     * code : 1
     * msg : 操作成功
     * consumer_details : [{"cid":462,"order_no":"F20170809144940859006","user_id":114,"order_status":"押金充值","total_fee":0.01,"create_time":"2017-08-09 14:49:46"}]
     */

    private int code;
    private String msg;
    private List<ConsumerDetailsBean> consumer_details;

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

    public List<ConsumerDetailsBean> getConsumer_details() {
        return consumer_details;
    }

    public void setConsumer_details(List<ConsumerDetailsBean> consumer_details) {
        this.consumer_details = consumer_details;
    }

    public static class ConsumerDetailsBean {
        /**
         * cid : 462
         * order_no : F20170809144940859006
         * user_id : 114
         * order_status : 押金充值
         * total_fee : 0.01
         * create_time : 2017-08-09 14:49:46
         */

        private int cid;
        private String order_no;
        private int user_id;
        private String order_status;
        private double total_fee;
        private String create_time;

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        private String pay_type;

        public String getBike_type() {
            return bike_type;
        }

        public void setBike_type(String bike_type) {
            this.bike_type = bike_type;
        }

        private String bike_type;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public double getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(double total_fee) {
            this.total_fee = total_fee;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}