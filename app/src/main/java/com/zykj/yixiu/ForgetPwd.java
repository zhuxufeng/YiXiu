package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPwd extends AppCompatActivity implements View.OnClickListener {

private EditText for_et_tel,for_et_yzm;
    private Button for_bt_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);
        for_et_tel= (EditText) findViewById(R.id.for_et_tel);
        for_et_yzm= (EditText) findViewById(R.id.for_et_yzm);
        for_bt_next= (Button) findViewById(R.id.for_bt_next);


        for_et_tel.setOnClickListener(this);
        for_et_yzm.setOnClickListener(this);
        for_bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.for_et_tel:
                String tel=for_et_tel.getText().toString();

                if(TextUtils.isEmpty(tel)){
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.for_bt_yzm:
                String yzm=for_et_yzm.getText().toString();
                if(TextUtils.isEmpty(yzm)){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

            case R.id.for_bt_next:
                Intent intent=new Intent(ForgetPwd.this,PassWord.class);
                startActivity(intent);
                break;

        }

    }
}
