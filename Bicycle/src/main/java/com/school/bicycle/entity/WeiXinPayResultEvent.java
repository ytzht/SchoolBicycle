package com.school.bicycle.entity;

/**
 * Created by Administrator on 2017/8/3.
 */

public class WeiXinPayResultEvent {

    private String flag;
    private int errcode;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public WeiXinPayResultEvent(String flag, int errcode) {

        this.flag = flag;
        this.errcode = errcode;
    }
}
