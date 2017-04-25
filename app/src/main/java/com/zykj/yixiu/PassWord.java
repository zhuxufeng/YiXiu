package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hss01248.dialog.StyledDialog;

import java.util.HashMap;
import java.util.Map;

public class PassWord extends AppCompatActivity implements View.OnClickListener {

    private EditText pwd_et_oldpwd, pwd_et_newpwd;
    private Button pwd_bt_ok;
    private String data;
    private String phone;
    private String vcode;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        pwd_et_oldpwd = (EditText) findViewById(R.id.pwd_et_oldpwd);
        pwd_et_newpwd = (EditText) findViewById(R.id.pwd_et_newpwd);
        pwd_et_newpwd.setOnClickListener(this);
        pwd_et_oldpwd.setOnClickListener(this);
        pwd_bt_ok = (Button) findViewById(R.id.pwd_bt_ok);


        pwd_bt_ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pwd_et_oldpwd:
                pwd_et_oldpwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                pwd_et_newpwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


                break;

            case R.id.pwd_bt_ok:
                final String old = pwd_et_oldpwd.getText().toString().trim();
                String newpwd = pwd_et_newpwd.getText().toString().trim();
                if (TextUtils.isEmpty(old)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newpwd)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!old.equals(newpwd)) {
                    return;
                }
                Map map = new HashMap();
                map.put("password", old);
                map.put("token",Y.TOKEN);

                //情求设置密码的接口
                Y.get(YURL.SET_PASSWORD, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //在成功后关闭dialog
                        StyledDialog.dismissLoading();
                        //判断返回的resp_code是否为 0
                        if (Y.getRespCode(result)) {
                            Y.t("密码设置成功");
//                                             User user = JSON.parseObject(Y.getData(result), User.class);
//                                             Y.user=user;
                            //成功之后跳转到登录页面
                            Intent intent = new Intent(PassWord.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            //返回的resp-code为 1
                            Y.t("密码设置失败");
                        }
                    }
                });


                break;


        }
    }
}
