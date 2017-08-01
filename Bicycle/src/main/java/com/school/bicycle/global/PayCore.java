package com.school.bicycle.global;



/**
 * 微信 & 支付宝支付
 *
 * @author Michael
 */

public class PayCore {

    private static PayCore mPayCore = null;

    private PayCore() {

    }

    public static PayCore getInstance() {
        if (null == mPayCore) {
            mPayCore = new PayCore();
        }
        return mPayCore;
    }

    public int mWeichatState = WeiChat_Pay_Normal;

    // -------------------- 微信支付 --------------------
    // 微信支付状态
    public static final int WeiChat_Pay_Normal = 0;
    public static final int WeiChat_Pay_Start = 1;
    public static final int WeiChat_Pay_Success = 2;
    public static final int WeiChat_Pay_Failed = 3;
    public static final int WeiChat_Pay_Cancle = 4;

}
