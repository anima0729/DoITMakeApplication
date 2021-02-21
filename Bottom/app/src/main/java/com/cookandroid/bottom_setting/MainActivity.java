package com.cookandroid.bottom_setting;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nhn.android.naverlogin.OAuthLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // Web Server Connect
    private static String TAG = "naver_profile";
    private static String IP = "172.18.32.1";
    String find_url = "http://" + IP + "/naver_profile.php";
    String insert_url = "http://" + IP + "/naver_profile_insert.php";

    // Bottom Navigation
    private Home home = new Home();
    private List list = new List();
    private History history = new History();
    private Statistics statistics = new Statistics();
    private Another another = new Another();

    // Login NAVER Token
    String accessToken;

    //Profile
    JSONObject profile;
    String gender;
    String nickname;
    String id;
    String find;

    //DB 객체
    public static List_DB_Open List_DB;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DB 테이블 초기화
        init_tables();
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

        // Naver 접근 토큰 받아오기
        Intent intent_main = new Intent(this.getIntent());
        accessToken = intent_main.getStringExtra("accessToken");
        if (accessToken != null) {
            // 토큰을 이용하여 프로필 정보 받아오기
            ApiMemberProfile apiMemberProfile = new ApiMemberProfile(accessToken);
            apiMemberProfile.start();
            // Web Server에서 요청을 받아 profile JSONObject 객체를 받아올 때까지 Main Thread를 잠시 멈춤
            try {
                apiMemberProfile.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            profile = apiMemberProfile.return_profile();
            // 받아온 정보를 PreferenceManager에 저장
            try {
                if (profile != null) {
                    id = (String) profile.getString("id");
                    gender = (String) profile.getString("gender");
                    nickname = (String) profile.getString("nickname");
                    // 추후 데이터를 Web Server에서 가져오는 게 될 경우 PreferenceManager 사용할 필요 X
                    PreferenceManager.setString(getApplicationContext(), "id", id);
                    PreferenceManager.setString(getApplicationContext(), "gender", gender);
                    PreferenceManager.setString(getApplicationContext(), "nickname", nickname);
                } else {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 이용자의 고유 Naver ID 값을 이용해 정보 불러오기
            selectDatabase selectDatabase = new selectDatabase(find_url, null);
            try {
                find = selectDatabase.execute(id).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 등록된 ID가 아닌 경우 Web Server에 아이디를 등록합니다.

            if (find.equals("null")) {
                InsertData insert_profile = new InsertData();
                insert_profile.execute(insert_url, id, nickname, gender);
                try {
                    find = selectDatabase.execute(id).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
    
    //DB 사용을 위해 table 초기화
    private void init_tables() {
        List_DB = new List_DB_Open(this) ;
        SQLiteDatabase db=List_DB.getWritableDatabase();
        //db.execSQL(List_DB_Make.SQL_DELETE) ; //DB 완전히 초기화
        List_DB.onCreate(db);
    }


    // Bring Data From WebServer
    class selectDatabase extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        private String url1;
        private ContentValues values1;
        String result1;


        public selectDatabase(String url, ContentValues contentValues) {
            this.url1 = url;
            this.values1 = contentValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String searchKeyword1 = params[0];
                String postParameters = "id=" + searchKeyword1;

                URL url = new URL(url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - "+ responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();


                return sb.toString().trim();

            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + s);

            if (s == null) {
                Toast.makeText(MainActivity.this, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                result1 = s;
            }
        }

    }

    // Insert Data To Web Server
    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String insert_id = (String)params[1];
            String insert_nickname = (String)params[2];
            String insert_gender = (String)params[3];

            String serverURL = (String)params[0];
            String postParameters = "id=" + insert_id + "&nickname=" + insert_nickname + "&gender=" + insert_gender;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();

                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }
}