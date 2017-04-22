package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import org.xutils.http.RequestParams;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class Personal extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_sett, ll_aboutus, ll_sevice, ll_msg, ll_address, ll_mywollet, ll_me;
    private ImageView per_iv_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ll_sett = (LinearLayout) findViewById(R.id.ll_sett);
        ll_aboutus = (LinearLayout) findViewById(R.id.ll_aboutus);
        ll_sevice = (LinearLayout) findViewById(R.id.ll_sevice);
        ll_msg = (LinearLayout) findViewById(R.id.ll_msg);
        per_iv_picture = (ImageView) findViewById(R.id.per_iv_picture);
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        ll_mywollet = (LinearLayout) findViewById(R.id.ll_mywollet);
        ll_me = (LinearLayout) findViewById(R.id.ll_me);
        ll_sett.setOnClickListener(this);
        ll_aboutus.setOnClickListener(this);
        ll_sevice.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        ll_me.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_mywollet.setOnClickListener(this);
        per_iv_picture.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //上传头像
            case R.id.per_iv_picture:
                Y.t("--------------------------------------------------------");
                GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 100) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    Glide.with(Personal.this).load(info.getPhotoPath()).into(per_iv_picture);
                                    RequestParams rp = new RequestParams(YURL.UPLOADICON);
                                    rp.addBodyParameter("icon", info.getPhotoPath());
                                    rp.addBodyParameter("token", Y.user.getToken());
                                    Y.post(rp, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            if (Y.getRespCode(result)) {
                                                Y.user.setIcon(Y.getData(result));
                                            } else {
                                                Y.t("上传失败");
                                            }


                                        }
                                    });


                                }
                            }

                        }

                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;

            case R.id.ll_sett:
                Intent in = new Intent(this, Appliancerepair.class);
                startActivity(in);
                break;
            case R.id.ll_aboutus:
                Intent inte = new Intent(this, Computerpair.class);
                startActivity(inte);
                break;
            case R.id.ll_sevice:
                Intent intent = new Intent(this, Mobilerepair.class);
                startActivity(intent);
                break;
            case R.id.ll_msg:


                break;
            case R.id.ll_me:
                Intent inme = new Intent(this, Me.class);
                startActivity(inme);

                break;
            case R.id.ll_address:
                Intent inadd = new Intent(this, Addressd.class);
                startActivity(inadd);

                break;
            case R.id.ll_mywollet:
                Intent inwollet = new Intent(this, MyWollet.class);
                startActivity(inwollet);

                break;

        }

    }
}
