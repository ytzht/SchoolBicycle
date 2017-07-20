package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/20.
 */

public class Wallet {


    /**
     * code : 1
     * balance : 0
     * share_income : 0
     * deposit_money : 10
     */

    private int code;
    private String balance;
    private String share_income;
    private String deposit_money;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
