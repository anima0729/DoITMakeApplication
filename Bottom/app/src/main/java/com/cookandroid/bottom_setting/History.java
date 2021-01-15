package com.cookandroid.bottom_setting;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class History extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history, container, false);

        final ListView listview ;
        final History_CustomChoiceListViewAdapter adapter;

        // 체크박스
        boolean mClick = false;

        /*
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        */

        // Adapter 생성
        adapter = new History_CustomChoiceListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview2);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("project1", "2020.09.21.M", "2022.03.20.S", "c++"
                , getResources().getDrawable(R.drawable.icon1),
                getResources().getDrawable(R.drawable.o)) ;
        // 두 번째 아이템 추가.
        adapter.addItem("project2", "2020.09.20.S", "2020.09.25.F","c#" + '\n' + "windowsprograming"
                ,getResources().getDrawable(R.drawable.icon2),
                getResources().getDrawable(R.drawable.x) ) ;

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                List_Listview_Item item = (List_Listview_Item) parent.getItemAtPosition(position) ;

                String goalStr = item.getGoal() ;
                String sdateStr = item.getSdate() ;
                String edateStr = item.getEdate() ;
                String etcStr = item.getEtc() ;
                Drawable buildingDrawable = item.getBuilding() ;
                Drawable isDoneDrawable = item.getIsDone() ;

                // TODO : use item data.
            }
        }) ;

        return view;
    }
}