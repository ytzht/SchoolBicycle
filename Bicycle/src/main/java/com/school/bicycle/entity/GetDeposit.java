package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/17.
 */

public class GetDeposit {


    /**
     * code : 1
     * msg : 操作成功
     * deposit : 0.01
     */

    private int code;
    private String msg;
    private String deposit;

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

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
}
