package com.school.bicycle.global;


/**
 * Created by zht on 2017/04/19 17:09
 */

public interface Apis {
    String Base = "http://106.14.192.87/xyxapi/";

    String queryBikeListByDate = "order/queryBikeListByDate";

    String queryConsumDetails = "order/queryConsumDetails";

    String queryShareDetails = "order/queryShareDetails";

    String getMyAppoint = "order/getMyAppoint";

    String setDiviceToken = "order/setDiviceToken";

    String aboutUs = "order/aboutUs";

    String dayLeaseList = "order/dayLeaseList";

    String invite = "production/invite?phone=";

    String findNotPayRoute = "order/findNotPayRoute";

    String longLeaseOrder = "order/longLeaseOrder";

    String rechargeOrder = "order/rechargeOrder";

    String bikeAlarmList = "order/bikeAlarmList";

    String cashCoupon = "order/cashCoupon";

    String myCoupon = "order/myCoupon";

    String submitLeaseOrder = "order/submitLeaseOrder";

    String getMyRoute = "order/getMyRoute";

    String uploadLocation = "order/uploadLocation?location=";

    String checkJumpStatus = "order/checkJumpStatus";

    String unlocking = "order/unlocking";

    String overLongLeaseBike = "order/overLongLeaseBike";

    String leaseBicycle = "order/leaseBicycle";

    String dayLeaseOrder = "order/dayLeaseOrder";

    String submitDayLeaseMoney = "order/submitDayLeaseMoney";

    String checkLongLease = "order/checkLongLease";

    String sharedBikeList = "order/sharedBikeList";

    String checkBikeByNumber = "order/checkBikeByNumber";

    String overUseBike = "order/overUseBike";

    String submit = "order/submit";

    String FailureList = "user/failureList";

    String depositRefund = "user/depositRefund";

    String withdraw = "user/withdraw";

    String FailureReporting = "user/failureReporting";

    String userLogout = "user/userLogout";

    String getUserInfo = "user/getUserInfo";

    String wallet = "user/wallet";

    String cancelMyAppoint = "order/cancelMyAppoint";

    String myBike = "order/myBike";

    String getMyMessage = "user/getMyMessage";

    String validateUser = "user/validateUser?device_id=";

    String StartShareMyBike = "order/startShareMyBike";

    String UpDate = "";

    String dayLeaseLists = "order/sharedBikeList";

    String CancelShareMyBike = "order/cancelShareMyBike";
}
