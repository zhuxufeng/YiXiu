package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditAddress extends AppCompatActivity implements View.OnClickListener {
    //编辑姓名
private EditText edit_et_name;
    //编辑电话号
    private TextView edit_tv_tel;
    //编辑地址

private EditText edit_et_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


        }

    }
}
