package com.cookandroid.bottom_setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.ListFragment;

public class Another_System extends ListFragment {


    int LangSelectItem;
    int ThemeSelectItem;
    AlertDialog.Builder LangDlg;
    AlertDialog.Builder ThemeDlg;

    // 리스트 뷰에 추가할 항목 설정
    private static final String[] List_System = new String[]{"          공지", "          언어", "          알림 및 음향", "          테마"};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, List_System));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final View header_system = getLayoutInflater().inflate(R.layout.another_system_header, null, false);
        getListView().addHeaderView(header_system);


        // 언어 선택시 나타나는 언어 설정 다이얼로그 창
        final CharSequence[] ArrLangDlg = {"한국어", "English"};

        LangDlg = new AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        // 언어 다이얼로그 창 설정
        LangDlg.setTitle(getString(R.string.SelectLanguage))
                .setSingleChoiceItems(ArrLangDlg, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LangSelectItem = which;
                    }
                })
                .setNeutralButton(getString(R.string.Select), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(LangSelectItem == 0) {
                            PreferenceManager.setString(getContext(), "lang", "ko");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                        else if(LangSelectItem == 1) {
                            PreferenceManager.setString(getContext(), "lang", "en");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                    }
                })
                .setCancelable(true);

        // 테마 선택시 나타나는 테마 설정 다이얼로그 창
        final CharSequence[] ArrThemeDlg = {getString(R.string.BasicTheme), getString(R.string.BrownTheme), getString(R.string.YellowTheme), getString(R.string.DarkTheme)};

        ThemeDlg = new AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        // 테마 다이얼로그 창 설정
        ThemeDlg.setTitle(getString(R.string.SelectTheme))
                .setSingleChoiceItems(ArrThemeDlg, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThemeSelectItem = which;
                    }
                })
                // 테마 선택창
                .setPositiveButton(getString(R.string.SelectLanguage), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ThemeSelectItem == 0) {
                            PreferenceManager.setString(getContext(), "theme", "Basic");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                        else if (ThemeSelectItem == 1) {
                            PreferenceManager.setString(getContext(), "theme", "Brown");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                        else if (ThemeSelectItem == 2) {
                            PreferenceManager.setString(getContext(), "theme", "Yellow");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                        else if (ThemeSelectItem == 3) {
                            PreferenceManager.setString(getContext(), "theme", "Dark");
                            ActivityCompat.finishAffinity(getActivity());
                        }
                    }
                })
                .setCancelable(true);

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        getListView().setItemChecked(position, false);

        // 아이템 선택에 따른 실행
        switch(position) {
            case (1):
                Intent intent_Notice = new Intent(getActivity(), Another_Notice.class);
                startActivity(intent_Notice);
                break;
            case (2):
                LangDlg.show();
                break;
            case (3):
                Intent intent_Notification = new Intent(getActivity(), Another_Notification.class);
                startActivity(intent_Notification);
                break;
            case (4):
                ThemeDlg.show();
                break;
            default:
                break;
        }
    }
}