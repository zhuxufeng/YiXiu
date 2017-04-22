package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private ImageView home_im_per;
private LinearLayout ll_app,ll_com,ll_mob;
    private Banner banner;
    private TextView home_tv_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        banner= (Banner) findViewById(R.id.banner);
        home_im_per= (ImageView) findViewById(R.id.home_im_per);
        ll_app= (LinearLayout) findViewById(R.id.ll_app);
        ll_com= (LinearLayout) findViewById(R.id.ll_com);
        ll_mob= (LinearLayout) findViewById(R.id.ll_mob);
        home_tv_city= (TextView) findViewById(R.id.home_tv_city);
        home_tv_city.setOnClickListener(this);
        home_im_per.setOnClickListener(this);

        ll_mob.setOnClickListener(this);
        ll_app.setOnClickListener(this);
        ll_com.setOnClickListener(this);

        //设置图片加载器
        banner.setImageLoader(new MyImage());
        //设置图片集合
        List<Integer> images=new ArrayList<>();
        images.add(R.mipmap.u6);
        images.add(R.mipmap.u6);
        images.add(R.mipmap.u6);

        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_tv_city:
                OptionsPicke op=new OptionsPicke();
                op.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        
                    }
                });
                break;
            case R.id.ll_app:
                Intent in=new Intent(this,Appliancerepair.class);
                startActivity(in);
                break;
            case R.id.ll_com:
                Intent inte=new Intent(this,Computerpair.class);
                startActivity(inte);
                break;
            case R.id.ll_mob:
                Intent intent=new Intent(this,Mobilerepair.class);
                startActivity(intent);
                break;
            case R.id.home_im_per:
            Intent intent1=new Intent(this,Personal.class);
                startActivity(intent1);
                break;

        }

    }
}
