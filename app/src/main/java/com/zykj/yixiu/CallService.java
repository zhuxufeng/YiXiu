package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class CallService extends AppCompatActivity implements View.OnClickListener {
private LinearLayout call_ll_time,call_ll_add;
    private Button call_bt_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callservice);
        call_bt_ok= (Button) findViewById(R.id.call_bt_ok);
        call_ll_add= (LinearLayout) findViewById(R.id.call_ll_add);
        call_ll_time= (LinearLayout) findViewById(R.id.call_ll_time);
        call_ll_time.setOnClickListener(this);
        call_ll_add.setOnClickListener(this);
        call_bt_ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_ll_time:



                break;
            case R.id.call_ll_add:
                Intent add=new Intent(this,YiXiuMap.class);
                startActivity(add);
                break;
            case R.id.call_bt_ok:
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
