package com.school.bicycle.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ValidateUser implements Serializable{


    /**
     * code : 1
     * msg : 登陆成功
     * body : {"user_id":"用户编号","phone":"手机号","wallet":0,"device_id":"设备号","status":"手机验证状态（1：已验证，0：未验证）","reg_from":"注册来源","create_time":"创建时间"}
     */

    private int code;
    private String msg;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable {
        /**
         * user_id : 用户编号
         * phone : 手机号
         * wallet : 0
         * device_id : 设备号
         * status : 手机验证状态（1：已验证，0：未验证）
         * reg_from : 注册来源
         * create_time : 创建时间
         */

        private String user_id;
        private String phone;
        private int wallet;
        private String device_id;
        private int status;
        private String reg_from;
        private String create_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getWallet() {
            return wallet;
        }

        public void setWallet(int wallet) {
            this.wallet = wallet;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReg_from() {
            return reg_from;
        }

        public void setReg_from(String reg_from) {
            this.reg_from = reg_from;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
