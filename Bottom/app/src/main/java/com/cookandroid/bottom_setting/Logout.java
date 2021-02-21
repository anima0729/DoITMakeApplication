package com.cookandroid.bottom_setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.data.OAuthLoginState;

import static com.cookandroid.bottom_setting.Logout_GlobalAuthHelper.accountLogout;

public class Logout extends AppCompatActivity {

    Button btnsignout;
    // private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        btnsignout = findViewById(R.id.btn_logout);

        btnsignout.setOnClickListener(mLogoutListener);
    }

    /*
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_logout:
                signOut();
                finishAffinity();
                break;
        }
    }
    */

    //로그아웃
    Button.OnClickListener mLogoutListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Logout_GlobalAuthHelper.accountLogout(getApplicationContext(),Logout.this);
        }
    };

    public void directToMainActivity(Boolean result) {
        Intent intent = new Intent(Logout.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}