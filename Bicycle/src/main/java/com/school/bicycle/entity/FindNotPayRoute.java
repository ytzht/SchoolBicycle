package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/28.
 */

public class FindNotPayRoute {

    /**
     * code : 1
     * msg : 操作成功
     * time_span : 4
     * distance : 5.79
     * calories : 2
     * total_fee : 0.00
     * bike_number : 011130
     * balance : 0.0
     */

    private int code;
    private String msg;
    private String time_span;
    private String distance;
    private String calories;
    private String total_fee;
    private String bike_number;
    private double balance;

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

    public String getTime_span() {
        return time_span;
    }

    public void setTime_span(String time_span) {
        this.time_span = time_span;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
