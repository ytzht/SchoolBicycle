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

    String longLeaseOrder = "order/longLeaseOrder";

    String getMyRoute = "order/getMyRoute";

    String checkJumpStatus = "order/checkJumpStatus";

    String unlocking = "order/unlocking";

    String leaseBicycle = "order/leaseBicycle";

    String dayLeaseOrder = "order/dayLeaseOrder";

    String submitDayLeaseMoney = "order/submitDayLeaseMoney";

    String overUseBike = "order/overUseBike";

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

}
