package com.zykj.yixiu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Computerpair extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_brand,ll_model,ll_fault;
    private TextView com_tv_type,com_tv_brand,com_tv_model,com_tv_fault;
    List<ComputerBean> lists; //品牌的数据源
    int comIndex = -1;  //用于检测是否选择了品牌
    int caIndex = -1;  //用于检测是否选择了品牌
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computerrepair);
        ll_brand= (LinearLayout) findViewById(R.id.ll_brand);
        ll_model= (LinearLayout) findViewById(R.id.ll_model);
        ll_fault= (LinearLayout) findViewById(R.id.ll_fault);
        com_tv_type= (TextView) findViewById(R.id.com_tv_type);
        com_tv_brand= (TextView) findViewById(R.id.com_tv_brand);
        com_tv_fault= (TextView) findViewById(R.id.app_tv_fault);
        com_tv_model= (TextView) findViewById(R.id.com_tv_model);
        com_tv_type.setOnClickListener(this);
        com_tv_brand.setOnClickListener(this);
        com_tv_model.setOnClickListener(this);



        ll_brand.setOnClickListener(this);
        ll_model.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.com_tv_brand:

                Y.get(YURL.FIND_COMPUTER_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), ComputerBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Computerpair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器

                                    com_tv_brand.setText(lists.get(options1).getName());
                                    comIndex = lists.get(options1).getId(); // 当前选择的索引
                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ComputerBean cb : lists) {
                                strs.add(cb.getName());
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
            case R.id.com_tv_model:
                //检测是否选择了品牌
                if (comIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map<String,String> pid=new HashMap();
                    pid.put("category",caIndex+"");
                    pid.put("pid",comIndex+"");


                    Y.get(YURL.FIND_COMPUTER_MODEL,pid, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), ComputerBean.class);

                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(Computerpair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        com_tv_model.setText(lists.get(options1).getName());
                                        comIndex=lists.get(options1).getId();

                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ComputerBean cb : lists) {
                                    strs.add(cb.getName());
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
            case R.id.com_tv_type:

                Map<String,String> pi=new HashMap<>();
                pi.put("pid",comIndex+"");
                //发起请求
                Y.get(YURL.FIND_COMPUTER_CATEGORY,pi, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), ComputerBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Computerpair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    com_tv_type.setText(lists.get(options1).getName());
                                    caIndex=lists.get(options1).getId();

                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ComputerBean cb : lists) {
                                strs.add(cb.getName());
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
