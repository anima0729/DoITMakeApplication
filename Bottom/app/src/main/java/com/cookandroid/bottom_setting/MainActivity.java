package com.cookandroid.bottom_setting;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Home home = new Home();
    private List list = new List();
    private History history = new History();
    private Statistics statistics = new Statistics();
    private Another another = new Another();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 시작시 언어 변경하기
        String custom_lang = PreferenceManager.getString(getApplicationContext(), "lang");
        Locale locale = new Locale(custom_lang);
        Configuration config = new Configuration();
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // 시작시 테마 변경하기
        // !! 무조건 컨텐트 뷰보다 먼저 테마를 설정해줘야합니다 !!
        String custom_theme = PreferenceManager.getString(getApplicationContext(),"theme");
        if (custom_theme == "Brown") {
            setTheme(R.style.Brown);
        }
        else if (custom_theme == "Yellow") {
            setTheme(R.style.Yellow);
        }
        else if (custom_theme == "Dark") {
            setTheme(R.style.Dark);
        }
        else {
            setTheme(R.style.Basic);
        }
        // 컨텐트 뷰 설정
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, home).commit();

        // 바텀네비게이션 뷰 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, home).commit();
                                return true;
                            case R.id.list:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, list).commit();
                                return true;
                            case R.id.history:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, history).commit();
                                return true;
                            case R.id.statistics:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, statistics).commit();
                                return true;
                            case R.id.another:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, another).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}