package com.cookandroid.bottom_setting;

import android.app.ProgressDialog;
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

public class Another_account extends AppCompatActivity {

    Button delete_user;
    Button set_password;
    private FirebaseAuth firebaseAuth ;
    ProgressDialog progressDialog;
    EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_account);


        firebaseAuth = FirebaseAuth.getInstance();
        delete_user = findViewById(R.id.delete_user);
        set_password = findViewById(R.id.new_password);

        delete_user.setOnClickListener(deleteListener);
        set_password.setOnClickListener(setpasswordlistener);

        progressDialog = new ProgressDialog(this);

    }

    Button.OnClickListener deleteListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            //회원탈퇴를 클릭하면 회원정보를 삭제

            if(v == delete_user) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Another_account.this);
                alert_confirm.setMessage("정말 계정 삭제를 하시겠습니까?").setCancelable(false).setPositiveButton(("확인"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (firebaseAuth.getCurrentUser() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Another_account.this, " 계정이 삭제되었습니다.",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                }
                            });
                        }
                        else if (OAuthLoginState.OK.equals(OAuthLogin.getInstance().getState(getApplicationContext()))){
                            OAuthLogin.getInstance().logoutAndDeleteToken(getApplicationContext());
                            Toast.makeText(Another_account.this, " 계정이 삭제되었습니다.",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }
                });
                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Another_account.this, "취소", Toast.LENGTH_LONG).show();
                    }
                });
                alert_confirm.show();

            }
        }

    };

    Button.OnClickListener setpasswordlistener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (firebaseAuth.getCurrentUser() != null) {
                Intent find_password = new Intent(Another_account.this, Find_password.class);
                startActivity(find_password);
            }
        }

    };
}
