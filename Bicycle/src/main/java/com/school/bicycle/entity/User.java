package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/25.
 */

public class User {

    /**
     * code : 1
     * msg : 操作成功
     * name : 闰土
     * phone : 15553426817
     */

    private int code;
    private String msg;
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
