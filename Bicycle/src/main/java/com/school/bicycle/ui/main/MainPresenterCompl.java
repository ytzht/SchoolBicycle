package com.school.bicycle.ui.main;

import android.content.Context;
import android.location.Location;
import android.support.v4.content.ContextCompat;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.school.bicycle.R;

/**
 * Created by ytzht on 2017/06/08 下午10:21
 */

public class MainPresenterCompl implements IMainPresenter{

    IMainView iMainView;
    Context context;


    public MainPresenterCompl(Context context, IMainView iMainView){
        this.context = context;
        this.iMainView = iMainView;

    }


    @Override
    public void initLocation(AMap aMap) {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。

//以下三种模式从5.1.0版本开始提供
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
        myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
//        myLocationStyle.myLocationIcon(new BitmapDescriptor());//设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。


        myLocationStyle.strokeColor(ContextCompat.getColor(context, R.color.black));//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(ContextCompat.getColor(context, R.color.ser_grey));//设置定位蓝点精度圆圈的填充颜色的方法。
        myLocationStyle.strokeWidth(1);

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
//        aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
//        aMap.setTrafficEnabled(true);//显示实时路况图层，aMap是地图控制器对象。

        aMap.showIndoorMap(true);//显示室内地图
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

            }
        });
    }

    @Override
    public void downloadMap(AMap aMap) {
        //构造OfflineMapManager对象
        OfflineMapManager amapManager = new OfflineMapManager(context, new OfflineMapManager.OfflineMapDownloadListener() {
            @Override
            public void onDownload(int i, int i1, String s) {

            }

            @Override
            public void onCheckUpdate(boolean b, String s) {

            }

            @Override
            public void onRemove(boolean b, String s, String s1) {

            }
        });

        try {
            amapManager.downloadByCityCode("370611");//按照citycode下载
            amapManager.downloadByCityName("烟台");//按照cityname下载
        } catch (AMapException e) {
            e.printStackTrace();
        }

        amapManager.pause();//通过代码暂停地图的下载
        amapManager.restart();
        amapManager.stop();//停止所有当前正在执行的下载，包括下载队列中等待的部分。

        // 离线地图默认会下载到手机存储卡的“amap”目录下，也可以自定义路径：
        // 需要在 AMap 对象初始化之前进行，否则操作会无效。
        // 设置应用单独的地图存储目录
        MapsInitializer.sdcardDir = "自定义的目录";


        //获取离线地图列表   略
        //通过updateOfflineCityByName方法判断离线地图数据是否存在更新
        try {
            amapManager.updateOfflineCityByName("");
        } catch (AMapException e) {
            e.printStackTrace();
        }

        //删除离线地图
        //执行 remove 操作时，需要等待 OfflineLoadedListener 回调之后才可以，否则（即使OfflineMapDownloadListener回调成功）操作将会无效。
        //删除某一城市的离线地图包
        amapManager.remove("");
    }


    private UiSettings mUiSettings;//定义一个UiSettings对象
    @Override
    public void initUISettings(AMap aMap) {
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
//        mUiSettings.setZoomControlsEnabled(true);

//        mUiSettings.setCompassEnabled(true);

//        aMap.setLocationSource();//通过aMap对象设置定位数据源的监听

        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮

        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置

        mUiSettings.setScaleControlsEnabled(true);

    }
}
