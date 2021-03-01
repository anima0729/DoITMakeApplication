package com.cookandroid.bottom_setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.data.OAuthLoginState;

import static com.cookandroid.bottom_setting.Logout_GlobalAuthHelper.accountLogout;

public class Logout extends AppCompatActivity {

    Button btnsignout;
    Button delete_user;
    Button set_password;
    private FirebaseAuth firebaseAuth ;
    ProgressDialog progressDialog;
    EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);


        btnsignout = findViewById(R.id.btn_logout);
        btnsignout.setOnClickListener(mLogoutListener);

        firebaseAuth = FirebaseAuth.getInstance();
        delete_user = findViewById(R.id.delete_user);
        set_password = findViewById(R.id.new_password);

        delete_user.setOnClickListener(deleteListener);
        set_password.setOnClickListener(setpasswordlistener);

        email=findViewById(R.id.email);
        progressDialog = new ProgressDialog(this);

    }

    Button.OnClickListener deleteListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            //회원탈퇴를 클릭하면 회원정보를 삭제
            if (firebaseAuth.getCurrentUser() != null) {
                if(v == delete_user) {
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Logout.this);
                    alert_confirm.setMessage("정말 계정 삭제를 하시겠습니까?").setCancelable(false).setPositiveButton(("확인"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Logout.this, " 계정이 삭제되었습니다.",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                }
                            });
                        }
                    });
                    alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Logout.this, "취소", Toast.LENGTH_LONG).show();
                        }
                    });
                    alert_confirm.show();

                }
            }
        }

    };

    Button.OnClickListener setpasswordlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (firebaseAuth.getCurrentUser() != null) {
                if (v==set_password){
                    progressDialog.setMessage("처리중입니다. 잠시 기다려 주세요");
                    progressDialog.show();

                    String emailAddress = email.getText().toString().trim();
                    firebaseAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Logout.this,"이메일을 보냈습니다", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                            else{
                                Toast.makeText(Logout.this,"메일을 보내지 못했습니다", Toast.LENGTH_LONG).show();

                            }

                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }

    };

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