package com.zykj.yixiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Alipay extends AppCompatActivity implements View.OnClickListener {
    //支付宝账号，支付宝姓名
private LinearLayout ali_ll_acc,ali_ll_name;
    private EditText ali_et_acc,ali_et_name;
    //支付宝 确定
    private Button ali_bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        ali_ll_name= (LinearLayout) findViewById(R.id.ali_ll_name);
        ali_ll_acc= (LinearLayout) findViewById(R.id.ali_ll_acc);
        ali_bt_ok= (Button) findViewById(R.id.ali_bt_ok);
        ali_bt_ok.setOnClickListener(this);
        ali_ll_acc.setOnClickListener(this);
        ali_ll_name.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ali_ll_name:
               String ali_name=ali_et_name.getText().toString();

                if(TextUtils.isEmpty(ali_name)){
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
            case R.id.ali_ll_acc:
                String ali_acc=ali_et_acc.getText().toString();
                if(TextUtils.isEmpty(ali_acc)){
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.ali_bt_ok:
                Toast.makeText(this, "填入成功", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
