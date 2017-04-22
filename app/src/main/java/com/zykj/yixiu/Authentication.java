package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import org.xutils.http.RequestParams;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class Authentication extends AppCompatActivity implements View.OnClickListener {
    private ImageView au_iv_zheng,au_iv_fan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        au_iv_fan= (ImageView) findViewById(R.id.au_iv_fan);
        au_iv_zheng= (ImageView) findViewById(R.id.au_iv_zheng);
        au_iv_zheng.setOnClickListener(this);
        au_iv_fan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.au_iv_zheng:
                GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 100) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    Glide.with(Authentication.this).load(info.getPhotoPath()).into(au_iv_zheng);
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
            case R.id.au_iv_fan:
                GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 100) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    Glide.with(Authentication.this).load(info.getPhotoPath()).into(au_iv_fan);
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
        }

    }
}
