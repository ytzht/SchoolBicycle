package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/27.
 */

public class DayLeaseOrder {

    /**
     * code : 1
     * msg : 操作成功
     * price : 50
     */

    private int code;
    private String msg;
    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
