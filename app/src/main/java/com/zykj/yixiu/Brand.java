package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Brand extends AppCompatActivity implements View.OnClickListener {
private LinearLayout ll_app,ll_com,ll_mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_brand);
        ll_app= (LinearLayout) findViewById(R.id.ll_app);
        ll_com= (LinearLayout) findViewById(R.id.ll_com);
        ll_mob= (LinearLayout) findViewById(R.id.ll_mob);


        ll_mob.setOnClickListener(this);
        ll_app.setOnClickListener(this);
        ll_com.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){



        }

    }
}
