package com.cookandroid.bottom_setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// 공지의 내용을 선택된 항목에 따라 바꾸는 액티비티 설정
// 그러므로 recycle
public class Another_Recycle_Notification extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_recycle_notification);

        String title;
        String writer;
        String date;
        String content;

        TextView title_text;
        TextView writer_text;
        TextView date_text;
        TextView content_text;

        title_text = (TextView) findViewById(R.id.notification_title);
        writer_text = (TextView) findViewById(R.id.notification_writer);
        date_text = (TextView) findViewById(R.id.notification_date);
        content_text = (TextView) findViewById(R.id.notification_content);

        //Intent 받아오기
        Intent intent_recycle = new Intent(this.getIntent());

        title = intent_recycle.getStringExtra("title");
        writer = intent_recycle.getStringExtra("writer");
        date = intent_recycle.getStringExtra("date");
        content = intent_recycle.getStringExtra("content");

        title_text.setText(title);
        writer_text.setText("작성자 : " + writer);
        date_text.setText("날짜 : " + date);
        content_text.setText(content);

    }
}
