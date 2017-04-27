package com.zykj.yixiu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/27.
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<AddressBean> listbean;


    @Override
    public int getCount() {
        return listbean.size();
    }

    @Override
    public Object getItem(int position) {
        return listbean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_addaddr, null);
           h=new ViewHolder(convertView);
            convertView.setTag(h);

        } else {
            h= (ViewHolder) convertView.getTag();

        }
        AddressBean add=listbean.get(position);
        h.name.setText(add.getName());
        h.tel.setText(add.getPhone());
        h.address.setText(add.getAddress());
        h.del.setTag(add.getAddress_id());
        h.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map m=new HashMap<>();
                m.put("user_id",Y.user.getUser_id());
                m.put("address_id",v.getTag());
                Y.get(YURL.DEL_ADDRESS,null,new Y.MyCommonCall<String>(){

                    @Override
                    public void onSuccess(String result) {
                        if(Y.getRespCode(result)){
                            Y.t("-----------");
                        }
                    }
                });
            }
        });


        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.tel)
        TextView tel;
        @Bind(R.id.address)
        TextView address;
        @Bind(R.id.iv_def)
        ImageView ivDef;
        @Bind(R.id.tv_def)
        TextView tvDef;
        @Bind(R.id.edit)
        TextView edit;
        @Bind(R.id.del)
        TextView del;
        @Bind(R.id.iv_set)
        ImageView ivSet;
        @Bind(R.id.tv_set)
        TextView tvSet;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
