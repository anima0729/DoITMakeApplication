package com.cookandroid.bottom_setting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class List extends Fragment {
    /*

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);

    }
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);


        final ListView listview ;
        final List_CustomChoiceListViewAdapter adapter;
        final Intent intent = new Intent(getActivity(),SelectGoal.class);
        // 체크박스
        boolean mClick = false;

        /*
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        */

        // Adapter 생성
        adapter = new List_CustomChoiceListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("project1", "2020.09.21.M", "2022.03.20.S", "c++"
                , getResources().getDrawable(R.drawable.icon1),
                "30%") ;
        // 두 번째 아이템 추가.
        adapter.addItem("project2", "2020.09.20.S", "2020.09.25.F","c#" + '\n' + "windowsprograming"
                , getResources().getDrawable(R.drawable.icon2),
                "70%" ) ;

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
                String percentStr = item.getPer() ;

                // TODO : use item data.
            }
        }) ;

        // add button에 대한 이벤트 처리. (미완성)
        ImageButton addButton = (ImageButton)view.findViewById(R.id.add) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);//액티비티 띄우기
            }

        }) ;

        // delete button에 대한 이벤트 처리. (미완성)
        // 선택해제 기능뿐.
        ImageButton deleteButton = (ImageButton)view.findViewById(R.id.delete) ;
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        // 함수 (미완성)
                        adapter.removeItem(i);
                    }
                }

                // 모든 선택 상태 초기화.
                listview.clearChoices() ;

                adapter.notifyDataSetChanged();
            }
        }) ;

        // selectAll button에 대한 이벤트 처리.

        final ImageButton selectAllButton = (ImageButton)view.findViewById(R.id.selectAll) ;
        selectAllButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int count = 0 ;
                count = adapter.getCount() ;
                int AllChecked = 0;

                for (int i=0; i<count; i++) {
                    // 전체선택되었는지 확인
                    if(listview.isItemChecked(i) == false) {
                        AllChecked = 1;
                        break;
                    }
                }

                // 전체선택
                if (AllChecked == 1) {
                    for (int i=0; i<count; i++) {
                        if (listview.isItemChecked(i) == false) {
                            listview.setItemChecked(i, true);
                        }
                    }
                }
                else { // 전체해제
                    for (int i=0; i<count; i++) {
                        listview.setItemChecked(i, false) ;
                    }
                }
            }
        }) ;

        // modify button에 대한 이벤트 처리. (미완성)
        final ImageButton modifyButton = (ImageButton)view.findViewById(R.id.modify) ;
        modifyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(selectAllButton.getVisibility() == View.GONE) {
                    selectAllButton.setVisibility(View.VISIBLE);
                    adapter.toggleCheckBox(true);
                } else {
                    selectAllButton.setVisibility(View.GONE);
                    adapter.toggleCheckBox(false);
                }
            }
        }) ;

        return view;
    }
}