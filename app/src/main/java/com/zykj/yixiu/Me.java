package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.xutils.http.RequestParams;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class Me extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout me_ll_changepicture,me_ll_tel;
    private ImageView me_iv_icon,me_iv_con;
    private TextView me_tv_dq;
    private Button me_bt_ok;
    private EditText me_et_name,me_et_tel;
private RadioButton me_rb_boy,me_rb_girl;
    private RadioGroup rg;
        private  String sex="男";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        me_ll_changepicture= (LinearLayout) findViewById(R.id.me_ll_changepicture);
        me_ll_changepicture.setOnClickListener(this);
        me_iv_icon= (ImageView) findViewById(R.id.me_iv_icon);
        me_iv_con= (ImageView) findViewById(R.id.me_iv_con);
        me_et_name= (EditText) findViewById(R.id.me_et_name);
        me_et_tel= (EditText) findViewById(R.id.me_et_tel);
        me_rb_boy= (RadioButton) findViewById(R.id.me_rb_boy);
        me_rb_girl= (RadioButton) findViewById(R.id.me_rb_girl);
        me_et_tel.setOnClickListener(this);
        me_et_name.setOnClickListener(this);
        me_iv_con.setOnClickListener(this);
        me_iv_icon.setOnClickListener(this);
        me_ll_tel= (LinearLayout) findViewById(R.id.me_ll_tel);
        me_tv_dq= (TextView) findViewById(R.id.me_tv_dq);
        me_tv_dq.setOnClickListener(this);
        me_ll_tel.setOnClickListener(this);
        me_bt_ok= (Button) findViewById(R.id.me_bt_ok);
        me_bt_ok.setOnClickListener(this);
        rg= (RadioGroup) findViewById(R.id.rg);





        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.me_rb_boy:
                        sex="男";
                        me_rb_boy.setChecked(true);
                        break;

                    case R.id.me_rb_girl:
                        sex="女";
                        me_rb_girl.setChecked(true);
                                break;
                }
                Y.user.setSex(sex);
            }
        });
        if (!TextUtils.isEmpty(Y.user.getSex())){
            switch (Y.user.getSex()){
                case "男":
                    me_rb_boy.setChecked(true);
                    break;
                case "女":
                    me_rb_girl.setChecked(true);
                    break;
            }
        }
        if(!TextUtils.isEmpty(Y.user.getUsername())){
            me_et_name.setText(Y.user.getUsername());
        }
        if(!TextUtils.isEmpty(Y.user.getIcon())){
            Glide.with(this).load(YURL.HOST+Y.user.getIcon()).into(me_iv_icon);
        }
        if(!TextUtils.isEmpty(Y.user.getPhone())){
           me_et_tel.setText(Y.user.getPhone());
        }
        if(!TextUtils.isEmpty(Y.user.getSex())){

        }
        if (!TextUtils.isEmpty(Y.user.getProvince())||!TextUtils.isEmpty(Y.user.getCity())){
            me_tv_dq.setText(Y.user.getProvince()+Y.user.getCity());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_iv_icon:
                //上传头像
                GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 100) {
                            if (resultList != null) {
                                for (PhotoInfo info : resultList) {
                                    Glide.with(Me.this).load(info.getPhotoPath()).into(me_iv_icon);
                                  //  me_iv_con.setVisibility(View.INVISIBLE);
                                    RequestParams rp = new RequestParams(YURL.UPLOADICON);
                                    rp.addBodyParameter("icon", info.getPhotoPath());
                                    rp.addBodyParameter("token", Y.user.getToken());
                                    Y.post(rp, new Y.MyCommonCall<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            if (Y.getRespCode(result)) {
                                                Y.user.setIcon(Y.getData(result));
                                             //   me_iv_con.setVisibility(View.INVISIBLE);


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


            case R.id.me_et_tel:



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
            case R.id.me_bt_ok:
                String dq=me_tv_dq.getText().toString().trim();
                String name=me_et_name.getText().toString().trim();
                String tel=me_et_tel.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;

                }
                RequestParams pr=new RequestParams(YURL.UPLOADICON);
                pr.addBodyParameter("name",name);
                pr.addBodyParameter("province",dq);
                pr.addBodyParameter("sex",sex);
                pr.addBodyParameter("phone",tel);
                Y.post(pr,new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {


                    }
                });


                Intent meOk=new Intent(this,Personal.class);
                startActivity(meOk);
                break;



        }

    }
}
