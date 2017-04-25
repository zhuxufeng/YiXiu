package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText re_et_tel, re_et_yzm;
    private Button re_bt_ok;
    private String tel;
    private String yzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        re_bt_ok = (Button) findViewById(R.id.re_bt_ok);
        re_et_yzm = (EditText) findViewById(R.id.re_et_yzm);
        re_et_tel = (EditText) findViewById(R.id.re_et_tel);
        re_et_tel.setOnClickListener(this);
        re_et_yzm.setOnClickListener(this);
        re_bt_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_et_tel:


                break;
            case R.id.re_et_yzm:

                break;

            case R.id.re_bt_ok:
                tel = re_et_tel.getText().toString();

                if (TextUtils.isEmpty(tel)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Y.isMobileNO(tel)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();


                }
                yzm = re_et_yzm.getText().toString();
                if (TextUtils.isEmpty(yzm)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断用户输入的验证的长度是否大于或者小于4

                if (yzm.length() != 4) {

                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

                Map<String, String> map = new HashMap<String, String>();

                map.put("phone", tel);//phone 电话号码

                map.put("vcode", yzm);//vcode 验证码

                map.put("type", "0");//type 固定值，0

                Y.get(YURL.REGISTER, map, new Y.MyCommonCall<String>() {

                    @Override
                    public void onSuccess(String result) {
                        //如果成功后吧dialog关闭
                        StyledDialog.dismissLoading();
                        //把返回的message取出来
                        String message = JSON.parseObject(result).getString("message");
                        if (Y.getRespCode(result)) {
                            Y.t("注册成功" + message);
                            Y.i("注册成功" + message);
                            //把成功后的data 取出来
                            String data =   Y.getData(result);
                            Y.i(data);
                            Y.TOKEN=data;

                            //成功之后跳转带密码的页面
                            Y.i(data+"+----------------------------+++");
                            //跳转
                            Intent intent = new Intent(Register.this, PassWord.class);
                                      ;
                            startActivity(intent);
                        } else {
                            //失败
                            Y.t("--11" + message);
                            Y.i("注册+++++++" + message);
                        }

                    }
                });
                break;

        }


    }
}
