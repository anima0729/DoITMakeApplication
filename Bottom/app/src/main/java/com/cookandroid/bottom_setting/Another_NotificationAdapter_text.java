package com.cookandroid.bottom_setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.bottom_setting.R;

import java.util.ArrayList;

// 텍스트 형태의 아이템을 넣을 때 쓸 어댑터 설정
public class Another_NotificationAdapter_text extends BaseAdapter {

    private Context notificationContext;
    private ArrayList<String> array_notification_text;

    private ViewHolder notificationViewHolder;

    public Another_NotificationAdapter_text(Context notificationContext, ArrayList<String> array_notification) {
        this.notificationContext = notificationContext;
        this.array_notification_text = array_notification;
    }

    @Override
    public int getCount() {
        return array_notification_text.size();
    }

    @Override
    public Object getItem(int position) {
        return array_notification_text.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(notificationContext).inflate(R.layout.another_layout_listview_text,parent,false);
            notificationViewHolder = new ViewHolder(convertView);
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            layoutParams.height = 90;
            convertView.setTag(notificationViewHolder);
        } else {
            notificationViewHolder = (ViewHolder) convertView.getTag();

        }

        notificationViewHolder.txt_name.setText(array_notification_text.get(position));

        return convertView;
    }

    public class ViewHolder {
        private TextView txt_name;

        public ViewHolder(View convertView) {
            txt_name = (TextView)convertView.findViewById(R.id.Listview_text);
        }
    }
}
