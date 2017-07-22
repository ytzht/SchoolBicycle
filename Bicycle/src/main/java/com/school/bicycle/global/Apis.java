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

    String getMyRoute = "order/getMyRoute";

    String FailureList = "user/failureList";

    String FailureReporting = "user/failureReporting";

    String userLogout = "user/userLogout";

    String wallet = "order/wallet";

    String getMyMessage = "user/getMyMessage";

    String validateUser = "user/validateUser?device_id=";

    String StartShareMyBike = "order/startShareMyBike";

}
