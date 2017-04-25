package com.zykj.yixiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hss01248.dialog.StyledDialog;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button bt_log;
    private TextView tv_reg,tv_fgpwd;
    private EditText et_tel,et_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_tel= (EditText) findViewById(R.id.et_tel);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        bt_log= (Button) findViewById(R.id.bt_log);
        tv_reg= (TextView) findViewById(R.id.tv_reg);
        tv_fgpwd= (TextView) findViewById(R.id.tv_fgpwd);
        et_tel.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
        bt_log.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
        tv_fgpwd.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_log:
               String tel=et_tel.getText().toString();
                String pwd=et_pwd.getText().toString();
                if(TextUtils.isEmpty(tel)){
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //创建map的集合 把需要的数据传上去
                Map<String,String> map=new HashMap<String, String>();
                map.put("phone",tel);//phone 电话号码
                map.put("password",pwd);// password  密码
                Y.get(YURL.LOGIN,map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //成功后关闭dialog
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            JSONObject object = JSON.parseObject(result);
                            String data = object.getString("data");
                            Y.user = JSON.parseObject(data, User.class);

                            Y.t("登录成功");
                            //成功之后跳转页面 调到主页面
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                        }else {
                            StyledDialog.dismissLoading();
                            //失败
                            Y.t("登录失败");

                        }
                    }
                });
                Y.get(YURL.LOGIN, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();

                    }
                });
              /*  String tel=et_tel.getText().toString();

                if("13604600261".equals(tel)){
                    Intent i=new Intent(this,Home.class);
                    startActivity(i);
                }
*/
                break;
            case R.id.tv_fgpwd:
                Intent intent1=new Intent(this,ForgetPwd.class);
                startActivity(intent1);

                break;
            case R.id.tv_reg:

                Intent in=new Intent(this,Register.class);
                startActivity(in);
                break;

        }

    }
}
