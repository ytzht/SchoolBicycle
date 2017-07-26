package com.school.bicycle.entity;

/**
 * Created by ytzht on 2017/07/26 下午9:14
 */

public class PayInfo {


    /**
     * code : 1
     * msg : 操作成功
     * order_no : L20170726211348439001
     * pay_type : zfb
     * pay_info : partner="2088721345232205"&seller_id="viplecheng@163.com"&out_trade_no="L20170726211348439001"&subject="充值订单支付L20170726211348439001"&body="充值订单支付"&total_fee="100"&notify_url="http://106.14.192.87/xyxapi/pay/alipay/order/notify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&sign="bA3Z4H6H1b8OKpgcrqsER2%2FPGvntvcdndanO5AidBJuHUPjf7ZO6Vsk3GSmq1iil6O224b%2BpMBGJAjN56BBkRdh8qLY1aP4QA5seyBinknSI80jNn7m0jBpl4fp8RpPmlp6zb35uUFDQY1T0K1VTCmc%2BLjbDPOaoH9vEqEWtGivj%2FOD%2Bi1CoCkwALaFly73TUaydCdZTnbELF9Yfton0wdOzss%2Fje3VGadLMWrGMZ5e%2B6Xtdvmn4nmrhHYkK3EHHwFmukWvUBu5DLgkUep1NwC5GzfFD4drYJEYnKc3j0p8r5Ydh0goRVPUfeGbCGiA4QeOPUpUeGCFDfVMcV8f0ew%3D%3D"&sign_type="RSA"
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
