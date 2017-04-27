package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.http.RequestParams;

public class EditAddress extends AppCompatActivity implements View.OnClickListener {
    //编辑姓名
private EditText edit_et_name;
    //编辑电话号
    private EditText edit_et_tel;
    //编辑地址

private EditText edit_et_add;
    //保存
    private Button et_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        edit_et_name= (EditText) findViewById(R.id.edit_et_name);
        edit_et_tel= (EditText) findViewById(R.id.edit_et_tel);
        edit_et_add= (EditText) findViewById(R.id.edit_et_add);
        et_ok= (Button) findViewById(R.id.et_ok);
        et_ok.setOnClickListener(this);
        edit_et_add.setOnClickListener(this);
        edit_et_tel.setOnClickListener(this);
        edit_et_name.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_et_name:
                edit_et_name.getText().toString().trim();


                break;
            case R.id.edit_et_tel:
                Intent tel=new Intent(this,ChangeNum.class);
                startActivityForResult(tel,0);
                break;
            case R.id.edit_et_add:
                Intent add=new Intent(this,Aaaaa.class);
                startActivityForResult(add,1);


                break;
            case R.id.et_ok:
              Y.get(YURL.ADD_ADDRESS, null, new Y.MyCommonCall<String>() {
                  @Override
                  public void onSuccess(String result) {
                      RequestParams add=new RequestParams();
                      add.addBodyParameter("name","");

                  }
              });


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
        if(requestCode==0&&resultCode==0){
            String tel = data.getStringExtra("tel");
            edit_et_tel.setText(tel);

        }
    }
}
