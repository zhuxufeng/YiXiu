package com.zykj.yixiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import static com.zykj.yixiu.R.id.options1;

public class Appliancerepair extends AppCompatActivity implements View.OnClickListener {
private LinearLayout ll_brand,ll_model,ll_fault,ll_add;
    private TextView app_tv_brand,app_tv_fault,app_tv_model,app_tv_type;
    private Button app_bt_ok;
    List<ApplicationBean> lists; //品牌的数据源
    int appIndex = -1;  //用于检测是否选择了品牌
    int caIndex = -1;  //用于检测是否选择了品牌
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliancerepair);
        app_bt_ok= (Button) findViewById(R.id.app_bt_ok);
        ll_brand= (LinearLayout) findViewById(R.id.ll_brand);
        ll_model= (LinearLayout) findViewById(R.id.ll_model);
        ll_fault= (LinearLayout) findViewById(R.id.ll_fault);
        ll_add= (LinearLayout) findViewById(R.id.ll_add);
        app_tv_type= (TextView) findViewById(R.id.app_tv_type);
        app_tv_brand= (TextView) findViewById(R.id.app_tv_brand);
        app_tv_fault= (TextView) findViewById(R.id.app_tv_fault);
        app_tv_model= (TextView) findViewById(R.id.app_tv_model);
        app_tv_brand.setOnClickListener(this);
        app_tv_fault.setOnClickListener(this);
        app_tv_type.setOnClickListener(this);

        app_tv_model.setOnClickListener(this);
        app_bt_ok.setOnClickListener(this);

        ll_brand.setOnClickListener(this);
        ll_model.setOnClickListener(this);
        ll_fault.setOnClickListener(this);
        ll_add.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.app_tv_brand:
                Y.get(YURL.FIND_APPLIANCE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), ApplicationBean.class);

                            //创建选择器
                            final OptionsPickerView opv = new OptionsPickerView.Builder(Appliancerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器

                                    app_tv_brand.setText(lists.get(options1).getName());
                                    appIndex = lists.get(options1).getId(); // 当前选择的索引
                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (ApplicationBean ab : lists) {
                                strs.add(ab.getName());
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
            case R.id.app_tv_model:
                //检测是否选择了品牌
                if (appIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map<String,String> pid=new HashMap();
                    pid.put("category",caIndex+"");
                    pid.put("pid",appIndex+"");


                    Y.get(YURL.FIND_APPLIANCE_MODEL,pid, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), ApplicationBean.class);

                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(Appliancerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        app_tv_model.setText(lists.get(options1).getName());


                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<>();
                                for (ApplicationBean ab : lists) {
                                    strs.add(ab.getName());
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
            case R.id.app_tv_type:

                Map<String,String> pi=new HashMap<>();
                pi.put("pid",appIndex+"");

                //发起请求
                Y.get(YURL.FIND_APPLIANCE_CATEGORY,pi, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), ApplicationBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Appliancerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    app_tv_type.setText(lists.get(options1)
                                            .getName());
                                    caIndex=lists.get(options1).getId();
                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<>();
                            for (ApplicationBean ab : lists) {
                                strs.add(ab.getName());
                            }

                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();
                            StyledDialog.dismissLoading();


                        } else {
                            //失败
                            Y.t("数据解析失败");
                            StyledDialog.dismissLoading();

                        }
                    }
                });
                break;
           /* case R.id.app_tv_fault:
                //发起请求
                Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(Appliancerepair.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    app_tv_fault.setText(lists.get(options1).getName());

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

                        }
                    }
                });
                break;
*/
            case R.id.app_bt_ok:
                Intent intent=new Intent(this,CallService.class);
                startActivity(intent);
                break;
        }

    }
}
