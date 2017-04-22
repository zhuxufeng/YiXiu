package com.zykj.yixiu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * Created by zykj on 2017/4/20.
 */

public class Aaaaa extends Activity implements View.OnClickListener {
    private static final String TAG = "Aaaaa";
    private Button textView;
    private EditText editText;
    private MapView mapView;
    private LatLng latLng;
    private boolean isFirst = true;
    private LocationClient client;
    private BaiduMap baiduMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.aaaaaaaaaaa);

        editText = (EditText) findViewById(R.id.etttt);
        mapView = (MapView) findViewById(R.id.baiduvv);
        textView = (Button) findViewById(R.id.btttt);
        baiduMap = mapView.getMap();
        textView.setOnClickListener(this);
        initLocation();
        seletMap();

    }


    public void jieMa(LatLng latLng1) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng1));
        baiduMap.clear();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        OverlayOptions overlayOptions = new MarkerOptions().icon(bitmapDescriptor).position(latLng1);
        baiduMap.addOverlay(overlayOptions);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult != null) {
                    Toast.makeText(Aaaaa.this, reverseGeoCodeResult.getAddress(), Toast.LENGTH_SHORT).show();
                    editText.setText(reverseGeoCodeResult.getAddress());

                }
            }
        });
    }

    private void seletMap() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng1) {
                jieMa(latLng1);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                Toast.makeText(Aaaaa.this, mapPoi.getName(), Toast.LENGTH_SHORT).show();
                jieMa(mapPoi.getPosition());
                return false;
            }
        });
    }


    private void initLocation() {
        client = new LocationClient(this);//创建定位对象
        LocationClientOption option = new LocationClientOption();//配置
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        client.setLocOption(option);
        client.registerLocationListener(new BDLocationListener() {//监听事件
            @Override
            public void onReceiveLocation(final BDLocation bdLocation) {
                if (bdLocation == null || baiduMap == null) {
                    Toast.makeText(Aaaaa.this, "定位失败", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    MyLocationData data = new MyLocationData.Builder()//把定位信息交给百度地图
                            .accuracy(bdLocation.getRadius())
                            .latitude(bdLocation.getLatitude())
                            .longitude(bdLocation.getLongitude())
                            .build();
                    baiduMap.setMyLocationData(data);//把定位信息交给百度地图
                    //更新地图位置
                    //获取经纬度
                    latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                    if (isFirst) {
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                editText.setText(bdLocation.getAddrStr());
                            }
                        });
                        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                        baiduMap.animateMapStatus(statusUpdate);

                        baiduMap.clear();
                        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
                        OverlayOptions overlayOptions = new MarkerOptions().icon(bitmapDescriptor).position(latLng);
                        baiduMap.addOverlay(overlayOptions);

                        isFirst = false;
                    }

                }
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        String s = editText.getText().toString();
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.geocode(new GeoCodeOption()
                .city("哈尔滨")
                .address(s));
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(Aaaaa.this, "没有检索到结果 ", Toast.LENGTH_SHORT).show();
                } else {
                    LatLng location = result.getLocation();
                    baiduMap.clear();
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
                    OverlayOptions overlayOptions = new MarkerOptions().icon(bitmapDescriptor).position(location);
                    baiduMap.addOverlay(overlayOptions);
                    MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLng(location);
                    baiduMap.animateMapStatus(statusUpdate);
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        baiduMap.setMyLocationEnabled(true);
        if (!client.isStarted()) {
            client.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        baiduMap.setMyLocationEnabled(false);
        client.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}

