package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Consumption {

    /**
     * code : 1
     * msg : 操作成功
     * consumer_details : [{"cid":1,"order_no":"20170713194749933000","user_id":0,"order_status":"充值余额","total_fee":10},{"cid":2,"order_no":"20170713200054317001","user_id":0,"order_status":"充值余额","total_fee":10},{"cid":3,"order_no":"20170713200800012002","user_id":0,"order_status":"充值余额","total_fee":10},{"cid":4,"order_no":"20170713200900837003","user_id":0,"order_status":"充值余额","total_fee":10},{"cid":5,"order_no":"20170713203753549004","user_id":0,"order_status":"充值余额","total_fee":50},{"cid":6,"order_no":"20170713203800005005","user_id":0,"order_status":"充值余额","total_fee":10},{"cid":7,"order_no":"20170713203817065006","user_id":0,"order_status":"押金充值","total_fee":100}]
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
         * cid : 1
         * order_no : 20170713194749933000
         * user_id : 0
         * order_status : 充值余额
         * total_fee : 10
         */

        private int cid;
        private String order_no;
        private int user_id;
        private String order_status;
        private int total_fee;

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

        public int getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(int total_fee) {
            this.total_fee = total_fee;
        }
    }
}
