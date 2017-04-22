package com.zykj.yixiu;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/15.
 */

public class YURL {


    // 服务器地址
    public static   final  String BASE_HOST="http://221.207.184.124:7071/";


    //项目名称
    public  static   final  String HOST=BASE_HOST+"yxg/";


    //查找手机品牌
    public static   final  String  FIND_PHONE_BRAND=HOST+"findPhoneBrand";
    //查找电脑品牌
    public static   final  String  FIND_COMPUTER_BRAND=HOST+"findComputerBrand";
    //查找家电品牌
    public static   final  String  FIND_APPLIANCE_BRAND=HOST+"findByApplianceBrand";


    //根据手机品牌查找型号
    public static   final  String  FIND_PHONE_MODEL=HOST+"findPhoneModel";
    //根据电脑品牌查找型号
    public static   final  String  FIND_COMPUTER_MODEL=HOST+"findByComputerModel";
    //根据家电品牌查找型号
    public static   final  String  FIND_APPLIANCE_MODEL=HOST+"findByApplianceModel";

    //查询手机故障
    public static   final  String  FIND_PHONE_FAULT=HOST+"findPhoneFault";
    //查询电脑分类
    public static   final  String  FIND_COMPUTER_CATEGORY=HOST+"findComputerCategory";
    //查询家电分类
    public static   final  String  FIND_APPLIANCE_CATEGORY=HOST+"findApplianceCategory";
    //uploadicon  上传头像
    public static   final  String  UPLOADICON=HOST+"uploadIcon";
    //register
    public static   final  String  REGISTER=HOST+"register";
    //login
    public static   final  String  LOGIN=HOST+"login";
    //findallprovince 获得所有省
    public static   final  String  FIND_ALL_PROVINCE=HOST+"findallprovince";
    //根据省Id获得省列表
    public static   final  String  FIND_CITY_BY_PROCODE=HOST+"findCityByProCode";


}