package com.zykj.yixiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class Mobilerepair extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_brand, ll_model, ll_fault;
    private TextView mob_tv_brand, mob_tv_fault, mob_tv_model;
    List<MobileBean> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonerepair);
        ll_brand = (LinearLayout) findViewById(R.id.ll_brand);
        ll_model = (LinearLayout) findViewById(R.id.ll_model);
        ll_fault = (LinearLayout) findViewById(R.id.ll_fault);
        mob_tv_brand = (TextView) findViewById(R.id.mob_tv_brand);
        mob_tv_fault = (TextView) findViewById(R.id.mob_tv_fault);
        mob_tv_model = (TextView) findViewById(R.id.mob_tv_model);
        mob_tv_brand.setOnClickListener(this);
        mob_tv_model.setOnClickListener(this);
        mob_tv_fault.setOnClickListener(this);
        ll_brand.setOnClickListener(this);
        ll_model.setOnClickListener(this);
        ll_fault.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mob_tv_brand:
                //发起请求

                Y.get(YURL.FIND_PHONE_BRAND, null, new Y.MyCommonCall<String>() {

                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Mobilerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器

                                    mob_tv_brand.setText(lists.get(options1).getName());
                                    mobileIndex = options1; // 当前选择的索引
                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }

                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();


                        } else {
                            //失败
                            Y.t("数据解析失败");
                            StyledDialog.dismissLoading();


                        }
                    }
                });


                break;
            case R.id.mob_tv_model:
                //检测是否选择了品牌
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    RequestParams rp = new RequestParams(YURL.FIND_PHONE_MODEL);
                    rp.addBodyParameter("pid", lists.get(mobileIndex).getId() + "");
                    Y.get(YURL.FIND_PHONE_MODEL, null, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(Mobilerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        mob_tv_model.setText(lists.get(options1).getName());

                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (MobileBean mb : lists) {
                                    strs.add(mb.getName());
                                }

                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();


                            } else {
                                //失败
                                Y.t("数据解析失败");
                                StyledDialog.dismissLoading();


                            }
                        }
                    });


                }


                break;
            case R.id.mob_tv_fault:
                //发起请求
                new RequestParams(YURL.FIND_PHONE_FAULT);
                Y.get(YURL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Mobilerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    mob_tv_fault.setText(lists.get(options1).getName());

                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                                strs.add(mb.getName());
                            }

                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();


                        } else {
                            //失败
                            Y.t("数据解析失败");
                            StyledDialog.dismissLoading();


                        }
                    }
                });
                break;

        }

    }
}
