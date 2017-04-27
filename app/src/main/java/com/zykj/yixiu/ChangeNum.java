package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ChangeNum extends AppCompatActivity implements View.OnClickListener {
    //
    private Button chan_bt_ok;
    private EditText chan_et_tel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephonenumber);
        chan_et_tel= (EditText) findViewById(R.id.chan_et_tel);
        chan_bt_ok= (Button) findViewById(R.id.chan_bt_ok);
        chan_et_tel.setOnClickListener(this);
        chan_bt_ok.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chan_et_tel:




                break;
            case R.id.chan_bt_ok:
                Intent tel=new Intent(ChangeNum.this,Me.class);
                String a=chan_et_tel.getText().toString().trim();
                Intent ok=getIntent();
                ok.putExtra("tel",a);
                setResult(0,ok);
                finish();

                break;



        }

    }

}
