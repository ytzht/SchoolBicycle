package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/20.
 */

public class Wallet {


    /**
     * code : 1
     * msg : 操作成功
     * balance : 175.74
     * share_income : 0.0
     * deposit_money : 299
     * deposit_status : 1
     */

    private int code;
    private String msg;
    private String balance;
    private String share_income;
    private String deposit_money;
    private int deposit_status;

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getShare_income() {
        return share_income;
    }

    public void setShare_income(String share_income) {
        this.share_income = share_income;
    }

    public String getDeposit_money() {
        return deposit_money;
    }

    public void setDeposit_money(String deposit_money) {
        this.deposit_money = deposit_money;
    }

    public int getDeposit_status() {
        return deposit_status;
    }

    public void setDeposit_status(int deposit_status) {
        this.deposit_status = deposit_status;
    }
}
