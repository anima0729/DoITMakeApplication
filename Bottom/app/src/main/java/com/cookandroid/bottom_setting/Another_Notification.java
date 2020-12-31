package com.cookandroid.bottom_setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Another_Notification extends AppCompatActivity {

    private Context notificationContext;
    private ArrayList<String> array_notification_text_01;
    private ArrayList<String> array_notification_switch_01;
    private ListView notificationListView_01;
    private ListView notificationListView_02;
    private Another_NotificationAdapter_switch n_otificationAdapter_switch;
    private Another_NotificationAdapter_text n_otificationAdapter_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_notification);

        notificationListView_01 = findViewById(R.id.Notification_01);
        notificationListView_02 = findViewById(R.id.Notification_02);

        this.notificationContext = getApplicationContext();

        //Intent 받아오기
        Intent intent_notification = new Intent(this.getIntent());

        //NotificationListView Code
        final ArrayAdapter adapter_notification = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice);

        // 알림 부분에 넣을 리스트 아이템 추가
        array_notification_switch_01 = new ArrayList<>();
        array_notification_switch_01.add(getString(R.string.Notification_Sound));
        array_notification_switch_01.add(getString(R.string.Notification_Push));

        array_notification_text_01 = new ArrayList<>();
        array_notification_text_01.add(getString(R.string.Notification_Notification));

        // 어댑터 설정
        notificationListView_01.setAdapter(adapter_notification);
        notificationListView_02.setAdapter(adapter_notification);

        n_otificationAdapter_switch = new Another_NotificationAdapter_switch(notificationContext, array_notification_switch_01);
        notificationListView_01.setAdapter(n_otificationAdapter_switch);

        n_otificationAdapter_text = new Another_NotificationAdapter_text(notificationContext, array_notification_text_01);
        notificationListView_02.setAdapter(n_otificationAdapter_text);

    }
}
