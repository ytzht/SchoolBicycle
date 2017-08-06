package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class QueryShareDetails {


    /**
     * code : 1
     * msg : 操作成功
     * share_details : [{"sid":3,"order_no":"H223133135135","user_id":0,"bike_number":"011151","bike_type":"1","lease_type":"时租","order_status":"共享收入","share_income":30,"two_user_id":12,"use_time":"2017-07-21 10:48:22","create_time":"2017-07-26 10:48:26"}]
     */

    private int code;
    private String msg;
    private List<ShareDetailsBean> share_details;

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

    public List<ShareDetailsBean> getShare_details() {
        return share_details;
    }

    public void setShare_details(List<ShareDetailsBean> share_details) {
        this.share_details = share_details;
    }

    public static class ShareDetailsBean {
        /**
         * sid : 3
         * order_no : H223133135135
         * user_id : 0
         * bike_number : 011151
         * bike_type : 1
         * lease_type : 时租
         * order_status : 共享收入
         * share_income : 30
         * two_user_id : 12
         * use_time : 2017-07-21 10:48:22
         * create_time : 2017-07-26 10:48:26
         */

        private int sid;
        private String order_no;
        private int user_id;
        private String bike_number;
        private String bike_type;
        private String lease_type;
        private String order_status;
        private String share_income;
        private int two_user_id;
        private String use_time;
        private String create_time;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
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

        public String getBike_number() {
            return bike_number;
        }

        public void setBike_number(String bike_number) {
            this.bike_number = bike_number;
        }

        public String getBike_type() {
            return bike_type;
        }

        public void setBike_type(String bike_type) {
            this.bike_type = bike_type;
        }

        public String getLease_type() {
            return lease_type;
        }

        public void setLease_type(String lease_type) {
            this.lease_type = lease_type;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getShare_income() {
            return share_income;
        }

        public void setShare_income(String share_income) {
            this.share_income = share_income;
        }

        public int getTwo_user_id() {
            return two_user_id;
        }

        public void setTwo_user_id(int two_user_id) {
            this.two_user_id = two_user_id;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
