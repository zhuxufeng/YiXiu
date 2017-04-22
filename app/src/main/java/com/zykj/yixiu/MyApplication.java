package com.zykj.yixiu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

import static com.zykj.yixiu.Y.context;

/**
 * Created by zykj on 2017/3/29.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
context=this;

        x.Ext.init(this);
        StyledDialog.init(this);
        //设置主题1
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()

        .build();
//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)

        .build();

//配置imageloader
        ImageLoader imageloader = new MyImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(context, imageloader, theme)

                .setFunctionConfig(functionConfig)

        .build();
        GalleryFinal.init(coreConfig);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                //在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });



    }
}
