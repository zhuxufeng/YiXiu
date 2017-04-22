package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xutils.http.RequestParams;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class Me extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout me_ll_changepicture,me_ll_tel;
    private ImageView me_iv_icon;
    private TextView me_tv_dq;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        me_ll_changepicture= (LinearLayout) findViewById(R.id.me_ll_changepicture);
        me_ll_changepicture.setOnClickListener(this);
        me_iv_icon= (ImageView) findViewById(R.id.me_iv_icon);
        me_iv_icon.setOnClickListener(this);
        me_ll_tel= (LinearLayout) findViewById(R.id.me_ll_tel);
        me_tv_dq= (TextView) findViewById(R.id.me_tv_dq);
        me_tv_dq.setOnClickListener(this);
        me_ll_tel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_ll_changepicture:
                GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 100) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    Glide.with(Me.this).load(info.getPhotoPath()).into(me_iv_icon);
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
            case R.id.me_et_name:
                break;
            case R.id.me_ll_tel:
                Intent chan_tel=new Intent(Me.this,ChangeNum.class);
                startActivity(chan_tel);
                break;
            case R.id.me_tv_dq:
                OptionsPicke op=new OptionsPicke();
                op.showOptionsPicke(this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {

                    }
                    
                });

                break;


        }

    }
}
