package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/6/28.
 */

public class Login {


    /**
     * code : 1
     * msg : 登录成功
     * deposit_status : 0
     * verify_status : 1
     */

    private int code;
    private String msg;
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
}
