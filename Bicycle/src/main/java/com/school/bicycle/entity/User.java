package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/25.
 */

public class User {

    /**
     * code : 1
     * msg : 操作成功
     * deposit_status : 1
     * verify_status : 1
     * name : 吕道欣
     * phone : 18153527447
     */

    private int code;
    private String msg;
    private int deposit_status;
    private int verify_status;
    private String name;
    private String phone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
