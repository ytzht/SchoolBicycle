package com.school.bicycle.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/17.
 */

public class CampusCardImage {

    /**
     * code : 1
     * msg : 提交成功
     * “image_url” : ”图片的url”
     */

    private int code;
    private String msg;
    private String Image_ur; // FIXME check this code

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

    public String get_Image_ur() {
        return Image_ur;
    }

    public void set_Image_ur(String Image_ur) {
        this.Image_ur = Image_ur;
    }
}
