package com.school.bicycle.ui.main;

import com.amap.api.maps.AMap;

/**
 * Created by ytzht on 2017/06/08 下午10:20
 */

public interface IMainPresenter {


    void initLocation(AMap aMap);

    void downloadMap(AMap aMap);

    void initUISettings(AMap aMap);

}
