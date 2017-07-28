package com.school.bicycle.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ValidateUser implements Serializable{


    /**
     * code : 1
     * msg : 登陆成功
     * body : {"user_id":25,"phone":"15553426817","wallet":0,"device_id":"862561039857212","deposit_status":0,"status":1,"alarm_switch":1,"reg_from":"android","create_time":"2017-07-27 13:00:16"}
     * name : 单宝滨
     * deposit_status : 1
     * verify_status : 0
     */

    private int code;
    private String msg;
    private BodyBean body;
    private String name;
    private int deposit_status;
    private int verify_status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeposit_status() {
        return deposit_status;
    }

    public void setDeposit_status(int deposit_status) {
        this.deposit_status = deposit_status;
    }

    public int getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(int verify_status) {
        this.verify_status = verify_status;
    }

    public static class BodyBean {
        /**
         * user_id : 25
         * phone : 15553426817
         * wallet : 0.0
         * device_id : 862561039857212
         * deposit_status : 0
         * status : 1
         * alarm_switch : 1
         * reg_from : android
         * create_time : 2017-07-27 13:00:16
         */

        private int user_id;
        private String phone;
        private double wallet;
        private String device_id;
        private int deposit_status;
        private int status;
        private int alarm_switch;
        private String reg_from;
        private String create_time;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public double getWallet() {
            return wallet;
        }

        public void setWallet(double wallet) {
            this.wallet = wallet;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public int getDeposit_status() {
            return deposit_status;
        }

        public void setDeposit_status(int deposit_status) {
            this.deposit_status = deposit_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAlarm_switch() {
            return alarm_switch;
        }

        public void setAlarm_switch(int alarm_switch) {
            this.alarm_switch = alarm_switch;
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
