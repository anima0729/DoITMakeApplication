package com.cookandroid.bottom_setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.ListFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Another_User extends ListFragment {

    // 리스트 뷰에 추가할 항목 설정
    private static String[] List_User = new String[]{"          계정", "          로그아웃", "          내보내기"};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, List_User));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 헤더 설정
        final View header_user = getLayoutInflater().inflate(R.layout.another_user_header, null, false);
        getListView().addHeaderView(header_user);
    }
    // 클릭시 설정
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        getListView().setItemChecked(position, false);

        switch(position) {
            case (1):
                Intent intent_account = new Intent(getActivity(), Another_account.class);
                startActivity(intent_account);
                break;

            case (2):

                Intent intent2 = new Intent(getActivity(),Logout.class);
                startActivity(intent2);
                break;
        }
    }
}
