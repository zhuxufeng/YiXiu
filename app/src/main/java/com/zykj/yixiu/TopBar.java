package com.zykj.yixiu;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;

/**
 * Created by zykj on 2017/4/11.
 */

public class TopBar extends RelativeLayout {
    //取出控件
    private ImageView left;
    private TextView right,center;
    //定义左侧属性
    private String leftText;
    private Drawable leftbg;
    private float leftSize;
    //定义右侧属性
    private String rightText;
    private Drawable rightColor;
    private float rightSize;
//定义中间属性
private String centerText;
    private int centerColor;


    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

   //左侧
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        leftbg=td.getDrawable(R.styleable.TopBar_leftbg);

        //右侧
        rightText=td.getString(R.styleable.TopBar_rightText);
        rightColor=td.getDrawable(R.styleable.TopBar_rightColor);
        rightSize=td.getDimension(R.styleable.TopBar_rightSize,0);
        //中间
       // centerSize=td.getFloat(R.styleable.TopBar_centerSize,0);
        centerText=td.getString(R.styleable.TopBar_centerText);
        centerColor=td.getColor(R.styleable.TopBar_centerColor, Color.parseColor("#00cccc"));
        //创建控件
        left=new ImageView(context);
        right=new TextView(context);
        center=new TextView(context);
        //属性设定控件


      left.setImageDrawable(leftbg);
        right.setText(rightText);
    //  right.setTextColor(rightColor);
        right.setGravity(Gravity.CENTER);
        center.setGravity(Gravity.CENTER);
        center.setText(centerText);
        center.setTextSize(25);
        center.setTextColor(centerColor);

        //控件设置到View中
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(left,lp);

        LayoutParams cp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cp.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(center,cp);

        LayoutParams rp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rp.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(right,rp);


    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
