package com.zykj.yixiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class YiXiuMap extends AppCompatActivity implements View.OnClickListener {

    private EditText map_et_shuru;
    private Button map_bt_ok;
    private MapView bmap;
    private BaiduMap map;
    private PoiSearch poiSearch;


    private BusLineSearch myBus;
    private String uid;
    private GeoCoder coder;

    private LocationClient myClient;
    private boolean isFirstLoc = true;
    private LatLng lat;
    private RoutePlanSearch search;
    private static final String TAG = "YiXiuMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.item_map);
        bmap = (MapView) findViewById(R.id.bmap);
        map_et_shuru = (EditText) findViewById(R.id.map_et_shuru);
        map_bt_ok = (Button) findViewById(R.id.map_bt_ok);
        map = bmap.getMap();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("普通");
        menu.add("卫星");
        menu.add("空白");
        menu.add("实时");
        menu.add("绘制");
        menu.add("文字");
        menu.add("几何");
        menu.add("弹出");
        menu.add("检索");
        menu.add("公交");
        menu.add("地理编码");
        menu.add("定位");
        menu.add("回到我的位置");
        menu.add("线路规划");
        return super.onCreateOptionsMenu(menu);

    }

    public void onGetResult(PoiResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            return;
        }
        for (PoiInfo poi : result.getAllPoi()) {
            if (poi.type == PoiInfo.POITYPE.BUS_LINE || poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
            }
            uid = poi.uid;
            // 当的到UID后再发起详细信息
            BusLineSearchOption bus = new BusLineSearchOption();
            bus.city("哈尔滨")
                    .uid(uid);
            myBus.searchBusLine(bus);

            break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        poiSearch = PoiSearch.newInstance();
        switch (item.toString()) {
            case "公交":
                PoiCitySearchOption op = new PoiCitySearchOption();
                op.city("哈尔滨");
                op.keyword("110");
                poiSearch.searchInCity(op);
                Log.i(TAG, "onOptionsItemSelected:" + "-----------------------------");


                break;
            case "检索":


                OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
                    public void onGetPoiResult(PoiResult result) {

                        //获取POI检索结果
                        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
                            Toast.makeText(YiXiuMap.this, "hahhahha", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(YiXiuMap.this, "wjelwjflkeajfdja", Toast.LENGTH_SHORT).show();

                        }
                        List<PoiInfo> allPoi = result.getAllPoi();
                        for (int i = 0; i < allPoi.size(); i++) {
                            Log.i(TAG, "onGetPoiResult: " + allPoi.get(i).address);
                            Log.i(TAG, "onGetPoiResult: " + allPoi.get(i).city);
                            Log.i(TAG, "onGetPoiResult: " + allPoi.get(i).name);

                        }


                    }

                    public void onGetPoiDetailResult(PoiDetailResult result) {
                        //获取Place详情页检索结果
                        Log.i(TAG, "onGetPoiDetailResult: ");
                    }

                    @Override
                    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

                    }
                };
                poiSearch.setOnGetPoiSearchResultListener(poiListener);
                poiSearch.searchInCity(new PoiCitySearchOption()
                        .city("北京")
                        .keyword("加油站")
                        .pageNum(10));
                //   poiSearch.destroy();

                break;
            case "绘制":
                final LatLng lng = new LatLng(45.7707710000, 126.6485440000);
                BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
                final OverlayOptions options = new MarkerOptions().position(lng).icon(descriptor);
                map.addOverlay(options);
                break;
            case "普通":
                map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case "卫星":
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case "空白":
                map.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case "实时":

                map.setTrafficEnabled(true);
                break;
            case "文字":
                LatLng text = new LatLng(45.7707710000, 126.6485440000);
                OverlayOptions textover = new TextOptions()
                        .bgColor(0xAAFFFF00)
                        .fontColor(0xFFFF00FF)
                        .fontSize(24)
                        .rotate(30)
                        .text("hello world")
                        .position(text);

                map.addOverlay(textover);
                break;
            case "几何":
                LatLng l1 = new LatLng(45.7699910000, 126.6512190000);
                LatLng l2 = new LatLng(45.7572660000, 126.6673260000);
                LatLng l3 = new LatLng(45.7732250000, 126.6577170000);
                LatLng l4 = new LatLng(45.7541950000, 126.6587650000);
                LatLng l5 = new LatLng(45.7513780000, 126.5937990000);
                List<LatLng> ll = new ArrayList<>();
                ll.add(l1);
                ll.add(l2);
                ll.add(l3);
                ll.add(l4);
                ll.add(l5);
                final OverlayOptions lay = new PolygonOptions()
                        .points(ll)
                        .stroke(new Stroke(5, 0xAA00FF00))
                        .fillColor(0xAA00FF00);
                map.addOverlay(lay);
                break;
            case "地理编码":
                coder = GeoCoder.newInstance();
                OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        if (geoCodeResult != null) {
                            Log.i(TAG, "onGetGeoCodeResult: " + geoCodeResult.getLocation().toString());
                        }
                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        if (reverseGeoCodeResult != null) {
                            Log.i(TAG, "onGetReverseGeoCodeResult: " + reverseGeoCodeResult.getAddress().toString());
                        }
                    }
                };
                coder.setOnGetGeoCodeResultListener(listener);
                coder.geocode(new GeoCodeOption().city("哈尔滨").address("银行街2号"));
                coder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(45.770768146615296, 126.64853653245628)));
                break;
            case "弹出":
                Button bt = new Button(this);
                bt.setBackgroundResource(R.mipmap.ic_launcher);
                LatLng ng = new LatLng(45.7707710000, 126.6485440000);
                InfoWindow tc = new InfoWindow(bt, ng, -258);
                map.showInfoWindow(tc);
                break;
            case "定位":
                //创建对象
                myClient = new LocationClient(this);
                //配置信息
                LocationClientOption option = new LocationClientOption();
                option.setCoorType("bd0911");//设置经纬度是百度的经纬度
                option.setOpenGps(true);//开启Gps
                option.setScanSpan(5000);//设置返回时间
                option.setIsNeedAddress(true);//设置地址
                myClient.setLocOption(option);//配置信息交给myclient对象
                myClient.start();
                //注册监听事件
                myClient.registerLocationListener(new BDLocationListener() {
                    @Override//定位成功
                    public void onReceiveLocation(BDLocation bdLocation) {
                        if (bdLocation == null || map == null) {
                            Toast.makeText(YiXiuMap.this, "定位失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(YiXiuMap.this, bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();

                        //数据转换成data
                        MyLocationData data = new MyLocationData.Builder()
                                .accuracy(bdLocation.getRadius())//半径
                                .latitude(bdLocation.getLatitude())//经度
                                .longitude(bdLocation.getLongitude())//纬度
                                .build();
                        //定位信息交给百度地图
                        map.setMyLocationData(data);
                        //获取经纬度
                        lat = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                        if (isFirstLoc) {
                            //更新百度地图
                            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(lat);
                            //更新信息交给百度地图
                            map.animateMapStatus(msu);
                            //不是第一次定位
                            isFirstLoc = false;
                        }
                        //标注
                        OverlayOptions over = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                                .position(lat);
                        map.addOverlay(over);
                    }

                    @Override
                    public void onConnectHotSpotMessage(String s, int i) {

                    }
                });
            case "回到我的位置":
                //更新信息
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(lat);
                //交给百度地图
                map.animateMapStatus(msu);

                break;
            case "线路规划":
                search = RoutePlanSearch.newInstance();
                OnGetRoutePlanResultListener roulis = new OnGetRoutePlanResultListener() {
                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                        //不行路线
                    }

                    @Override
                    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                        //火车路线
                        if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                            Toast.makeText(YiXiuMap.this, "未找到结果", Toast.LENGTH_SHORT).show();
                        }
                        if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                            transitRouteResult.getSuggestAddrInfo();
                            return;
                        }
                        if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {


                        }
                    }

                    @Override
                    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
                        //跨城综合公共交通线路
                    }

                    @Override
                    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                        //驾驶路线
                    }

                    @Override
                    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                    }

                    @Override
                    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                    }
                };
                search.setOnGetRoutePlanResultListener(roulis);
                PlanNode start = PlanNode.withCityNameAndPlaceName("哈尔滨", "医大一院");
                PlanNode end = PlanNode.withCityNameAndPlaceName("哈尔滨", "哈西服装城");
                search.masstransitSearch(new MassTransitRoutePlanOption().from(start).to(end));

                break;
        }
        return super.onOptionsItemSelected(item);


    }


  /*  @Override
    protected void onStart() {
        super.onStart();
        map.setMyLocationEnabled(true);
        if(!myClient.isStarted()){
            myClient.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.setMyLocationEnabled(false);
        myClient.stop();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmap.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmap.onPause();
    }


    @OnClick({R.id.map_et_shuru, R.id.map_bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map_et_shuru:
                break;
            case R.id.map_bt_ok:

                map_bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = map_et_shuru.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            Toast.makeText(YiXiuMap.this, "请输入正确的地址", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            GeoCoder ge = GeoCoder.newInstance();
                            ge.geocode(new GeoCodeOption().city("哈尔滨市").address(s));
                            ge.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                                @Override
                                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                                    if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                                        Toast.makeText(YiXiuMap.this, "请输入合法地址", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(YiXiuMap.this, "添加地址成功", Toast.LENGTH_SHORT).show();
                                        MapStatusUpdate ms = MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation());
                                        map.animateMapStatus(ms);
                                    }

                                }

                                @Override
                                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                                }
                            });
                        }
                    }
                });
                //点击地图单击事件
                map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                    @Override//地图点击
                    public void onMapClick(LatLng latLng) {
                        //调用解码
                        decode(latLng);
                        //设置图片
                        setBitmap(latLng);

                    }

                    @Override//兴趣点
                    public boolean onMapPoiClick(MapPoi mapPoi) {
                        mapPoi.getPosition();
                        mapPoi.getName();

                        return true;
                    }
                });
                break;
        }
    }

    //标注
    public void setBitmap(LatLng la) {
        map.clear();
        OverlayOptions ov = new MarkerOptions()
                .position(la)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        map.addOverlay(ov);

    }

    //解码
    public void decode(LatLng lat) {
        //创建对象
        GeoCoder coder = GeoCoder.newInstance();
        //反地理
        coder.reverseGeoCode(new ReverseGeoCodeOption().location(lat));
        coder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            //地理
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            //反地理
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult != null) {
                    Toast.makeText(YiXiuMap.this, reverseGeoCodeResult.getAddress(), Toast.LENGTH_SHORT).show();
                    map_et_shuru.setText(reverseGeoCodeResult.getAddress());
                }
            }
        });
    }
}
