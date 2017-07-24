package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/24.
 */

public class Mybiycle {


    /**
     * code : 1
     * msg : 操作成功
     * bike_number : 011190
     * share_income : 6.0
     */

    private int code;
    private String msg;
    private String bike_number;
    private String share_income;

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

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public String getShare_income() {
        return share_income;
    }

    public void setShare_income(String share_income) {
        this.share_income = share_income;
    }
}
