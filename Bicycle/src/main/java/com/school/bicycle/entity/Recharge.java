package com.school.bicycle.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class Recharge {


    /**
     * code : 1
     * msg : 操作成功
     * recharge : [{"pro_id":1,"pro_name":"10元","price":10,"recharge_amont":9.99,"pro_img":"","pro_desc":""},{"pro_id":2,"pro_name":"50元","price":50,"recharge_amont":49.95,"pro_img":"","pro_desc":"充值50元"}]
     */

    private int code;
    private String msg;
    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    private List<RechargeBean> recharge;

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

    public List<RechargeBean> getRecharge() {
        return recharge;
    }

    public void setRecharge(List<RechargeBean> recharge) {
        this.recharge = recharge;
    }

    public static class RechargeBean {
        /**
         * pro_id : 1
         * pro_name : 10元
         * price : 10
         * recharge_amont : 9.99
         * pro_img :
         * pro_desc :
         */

        private String pro_id;
        private String pro_name;
        private String price;
        private double recharge_amont;
        private String pro_img;
        private String pro_desc;

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public double getRecharge_amont() {
            return recharge_amont;
        }

        public void setRecharge_amont(double recharge_amont) {
            this.recharge_amont = recharge_amont;
        }

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
        }

        public String getPro_desc() {
            return pro_desc;
        }

        public void setPro_desc(String pro_desc) {
            this.pro_desc = pro_desc;
        }
    }
}
