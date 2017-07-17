package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/17.
 */

public class GetDeposit {


    /**
     * code : 1
     * msg : 操作成功
     * deposit : 10
     */

    private int code;
    private String msg;
    private int deposit;

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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
