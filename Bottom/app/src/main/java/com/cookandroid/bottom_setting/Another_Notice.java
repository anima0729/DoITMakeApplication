package com.cookandroid.bottom_setting;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Another_Notice extends AppCompatActivity {

    private ListView noticeListView;
    private Another_Notice_Adapter noticeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_notice);

        //Intent 받아오기
        Intent intent_notice = new Intent(this.getIntent());

        // 리스트뷰 어댑터 설정
        noticeAdapter = new Another_Notice_Adapter();
        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeListView.setAdapter(noticeAdapter);

        //
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent recycle_intent = new Intent(getApplicationContext(), Another_Recycle_Notification.class);
                startActivity(recycle_intent);

            }
        });
    }
}