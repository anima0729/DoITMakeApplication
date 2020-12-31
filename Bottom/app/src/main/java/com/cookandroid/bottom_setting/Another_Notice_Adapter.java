package com.cookandroid.bottom_setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Another_Notice_Adapter extends BaseAdapter {

    private ArrayList<Another_Notice_List_Item> listViewItemList = new ArrayList<Another_Notice_List_Item>();

    public Another_Notice_Adapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.another_notice_list, parent, false);
        }

        // 데이터 베이스에서 제목 이름 날짜 등의 데이터 받아오기
        TextView noticetitle = (TextView) convertView.findViewById(R.id.noticeText);
        TextView noticename = (TextView) convertView.findViewById(R.id.nameText);
        TextView noticedate = (TextView) convertView.findViewById(R.id.dateText);

        // 클릭 되었을 때의 position 값 받아오기
        Another_Notice_List_Item listitem = listViewItemList.get(pos);

        noticetitle.setText(listitem.getTitle());
        noticename.setText(listitem.getName());
        noticedate.setText(listitem.getDate());

        return convertView;
    }

    // 데이터베이스에서 아이디 받아와 리스트로 입력
    public void addItem(int id, String title, String name, String date) {
        Another_Notice_List_Item item = new Another_Notice_List_Item();

        item.setId(id);
        item.setTitle(title);
        item.setDate(date);
        item.setName(name);

        listViewItemList.add(item);
    }
}
