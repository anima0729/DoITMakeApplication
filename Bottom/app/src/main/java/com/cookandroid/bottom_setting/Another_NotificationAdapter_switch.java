package com.cookandroid.bottom_setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;

import java.util.ArrayList;

// 스위치 형태의 아이템을 넣을 때 쓸 어댑터 설정
public class Another_NotificationAdapter_switch extends BaseAdapter {

    private Context notificationContext;
    private ArrayList<String> array_notification_switch;

    private ViewHolder notificationViewHolder;

    public Another_NotificationAdapter_switch(Context notificationContext, ArrayList<String> array_notification_switch) {
        this.notificationContext = notificationContext;
        this.array_notification_switch = array_notification_switch;
    }

    @Override
    public int getCount() {
        return array_notification_switch.size();
    }

    @Override
    public Object getItem(int position) {
        return array_notification_switch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(notificationContext).inflate(R.layout.another_layout_listview_switch,parent,false);
            notificationViewHolder = new ViewHolder(convertView);
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            layoutParams.height = 120;
            convertView.setTag(notificationViewHolder);
        } else {
            notificationViewHolder = (ViewHolder) convertView.getTag();

        }

        notificationViewHolder.switch_name.setText(array_notification_switch.get(position));

        return convertView;
    }

    public class ViewHolder {
        private Switch switch_name;

        public ViewHolder(View convertView) {
            switch_name = (Switch)convertView.findViewById(R.id.Listview_switch);
        }
    }
}
