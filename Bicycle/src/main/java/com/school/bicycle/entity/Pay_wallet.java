package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/27.
 */

public class Pay_wallet {

    /**
     * code : 1
     * msg : 交费成功
     * pay_type : wallet
     */

    private int code;
    private String msg;
    private String pay_type;

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

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
}
