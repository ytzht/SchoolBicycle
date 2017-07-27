package com.school.bicycle.entity;

/**
 * Created by ytzht on 2017/07/26 下午9:14
 */

public class PayInfo {

    /**
     * code : 1
     * msg : 操作成功
     * order_no : L20170727162235436003
     * pay_type : zfb
     * pay_info : partner="2088721345232205"&seller_id="viplecheng@163.com"&out_trade_no="L20170727162235436003"&subject="充值订单支付L20170727162235436003"&body="充值订单支付"&total_fee="0.01"&notify_url="http://106.14.192.87/xyxapi/pay/alipay/order/notify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&sign="mdp7JBgzlgKj7e8oPmL2lTI1X%2Fv0kSrzHK149qGekbBq6N6a63shUDnh9GoH5S58A5v2fLZ9m61afZ0dpT%2B7Lz5MR1JTWa3WsjmnEVH9zfvHQyqnTs30XdKnC8s1UgqwwU8NWZNE0tgsjhK4TmoQtVF4%2FzL6h6XkfTbf%2Bm36eO4P%2B3C3Vux0UM1za6zjssiyB1zjM4Tb4m1z5S%2BIM9qrInDorUyWFQgTRBBkrB%2F1rMgRQzAiJSNnetU1XjeAVGIbe4oc1DZmDC0Mx3K6AT10fhNJmfgTO3Dx2dgMOApJGmRpFY5Thu%2F8PGc7Dm0282GPoyp%2FNsvxyESB8qbaEFF61w%3D%3D"&sign_type="RSA"
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
}
