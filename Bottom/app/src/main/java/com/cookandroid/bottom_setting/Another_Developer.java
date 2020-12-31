package com.cookandroid.bottom_setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class Another_Developer extends ListFragment {

    // 개발자 목록
    private static String[] List_Developer = new String[]{"          개발자", "          버전", "          피드백 및 평점"};

    //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, List_Developer));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final View header_developer = getLayoutInflater().inflate(R.layout.another_developer_header, null, false);
        getListView().addHeaderView(header_developer);
    }
    // 목록을 클릭했을 경우 실행
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        getListView().setItemChecked(position, false);

        switch(position) {
            // 개발자 버튼 클릭시
            case (1):
                Intent intent_introduce_developer = new Intent(getActivity(), Another_Introduce_Developer.class);
                startActivity(intent_introduce_developer);
                break;
            // 버전 버튼 클릭시
            case(2):
                Intent intent_version = new Intent(getActivity(), Another_Version.class);
                startActivity(intent_version);
                break;
            // 피드백 및 평점 버튼 클릭시
            case (3):
                final String appPackageName = getActivity().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            default:
                break;
        }
    }
}
