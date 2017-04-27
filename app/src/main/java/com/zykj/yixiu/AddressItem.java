package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class AddressItem extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout re_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_addaddr);
        re_add= (RelativeLayout) findViewById(R.id.re_add);
        re_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.re_add:
                Intent intent=new Intent(this,EditAddress.class);
                startActivity(intent);
                break;
        }

    }
}
