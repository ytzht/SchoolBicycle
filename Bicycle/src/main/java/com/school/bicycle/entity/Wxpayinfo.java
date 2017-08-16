package com.school.bicycle.entity;



/**
 * Created by Administrator on 2017/7/27.
 */

public class Wxpayinfo {


    /**
     * code : 1
     * msg : 操作成功
     * order_no : L20170727090437081000
     * pay_type : wx
     * pay_info : {
     "appid" :"wx71dafeac9c6868f8",
     "partnerid" :"1381611602",
     "prepayid" :"wx201707270904378e6153bd9c0481090541",
     "noncestr" :"hq7lgxDtbDRvIenu",
     "timestamp" :"1501117477",
     "package" :"Sign=WXPay",
     "sign" :"68C91A345F25C4E5CF30AEC8AE7EB9C5"
     }
     */

    private int code;
    private String msg;
    private String order_no;
    private String pay_type;
    private String pay_info;

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

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }

    public static class pay_info {
        public String appid;
        public String noncestr;

        public String partnerid;
        public String prepayid;
        public String timestamp;
        public String sign;

    }
}
