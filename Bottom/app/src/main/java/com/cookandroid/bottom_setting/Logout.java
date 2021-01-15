package com.cookandroid.bottom_setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {

    Button btnsignout;
    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);


    }

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
}