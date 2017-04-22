package com.zykj.yixiu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;
import java.util.logging.Logger;

/**
 * 工具类
 * Created by zykj on 2017/4/8.
 */

public  class Y {
    private static final String TAG = "Y";
    public static Context context; //全局上下文

    public  static  boolean isLog=true; //控制日志打印的开关


    //user的类

    public static User user;





    /**
     * 吐司功能只需要传入一个 字符串
     * @param str
     */
    public static  void t(String  str){
        Toast.makeText(context,str, Toast.LENGTH_LONG).show();
    }

    /**
     * 输出log日志
     * @param str
     */
    public static  void i(String  str){
        if(isLog)
            Log.i(TAG, "i: "+str);
    }


    /**
     * 检测请求返回的数据是否正确
     */
    public static boolean getRespCode(String  result){
        if("0".equals(JSON.parseObject(result).getString("resp_code"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 如果成功获取数据
     */
    public static String getData(String  result){
        return JSON.parseObject(result).getString("data");
    }





    /**
     * get请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable get(String url, Map<String,String> params, MyCommonCall<String> call){
        if(params==null)
            i(url);
        else
            i(url+params.toString());
        //请求的对象
        RequestParams rp  =new RequestParams(url);

        //检测外部是否传入了参数
        if(params!=null){
            //把参数取出来这是到rp
            for (Map.Entry<String,String> entry :params.entrySet()) {
                rp.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }
        // 只要发起Get请求就开启对话框
     //   StyledDialog.buildLoading().show();
        StyledDialog.buildLoading().show();
        return   x.http().get(rp, call);
    }
    /**
     * post请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable post(RequestParams params, MyCommonCall<String> call){
        return   x.http().post(params, call);
    }
    /**
       实现不需要外部完成的两个函数
     */
    public abstract  static class  MyCommonCall<String> implements Callback.CommonCallback<String>{
        @Override
        public void onFinished() {StyledDialog.dismissLoading();}

        @Override
        public void onCancelled(CancelledException cex) {}

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            t("服务器异常");
            ex.printStackTrace();
            StyledDialog.dismissLoading();

        }



    }





    public static boolean isMobileNO(String mobiles) {

  /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）

  总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */

        String tel = "[1][358]\\d{9}";
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(tel);
    }




}
