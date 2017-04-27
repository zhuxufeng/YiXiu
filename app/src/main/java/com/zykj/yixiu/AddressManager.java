package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class AddressManager extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout re_addaddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanager);
        re_addaddr= (RelativeLayout) findViewById(R.id.re_addaddr);
        re_addaddr.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.re_addaddr:
                Intent intent=new Intent(this,AddressItem.class);
                startActivity(intent);
                break;
        }

    }
}
