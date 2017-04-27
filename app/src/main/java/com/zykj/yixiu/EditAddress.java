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
    private EditText edit_et_tel;
    //编辑地址

private EditText edit_et_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        edit_et_name= (EditText) findViewById(R.id.edit_et_name);
        edit_et_tel= (EditText) findViewById(R.id.edit_et_tel);
        edit_et_add= (EditText) findViewById(R.id.edit_et_add);
        edit_et_add.setOnClickListener(this);
        edit_et_tel.setOnClickListener(this);
        edit_et_name.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_et_name:

                break;
            case R.id.edit_et_tel:
                break;
            case R.id.edit_et_add:
                Intent add=new Intent(this,Aaaaa.class);
                startActivityForResult(add,1);


                break;



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            String add = data.getStringExtra("add");
            edit_et_add.setText(add);

        }
    }
}
