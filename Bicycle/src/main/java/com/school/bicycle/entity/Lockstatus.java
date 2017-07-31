package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/7/31.
 */

public class Lockstatus {

    /**
     * code : 1
     * msg : 操作成功
     * lock_status : 0
     */

    private int code;
    private String msg;
    private int lock_status;

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

    public int getLock_status() {
        return lock_status;
    }

    public void setLock_status(int lock_status) {
        this.lock_status = lock_status;
    }
}
