package com.cookandroid.bottom_setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Firebase_Adapter extends ArrayAdapter {
    private boolean mClick = false;
    String Goal=null;
    public Firebase_Adapter(Context context, ArrayList users){
        super(context,0,users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView goalTextView = (TextView) convertView.findViewById(R.id.goal);
        TextView sdateTextView = (TextView) convertView.findViewById(R.id.sdate);
        TextView edateTextView = (TextView) convertView.findViewById(R.id.edate);
        TextView etcTextView = (TextView) convertView.findViewById(R.id.etc);
        TextView perTextView = (TextView) convertView.findViewById(R.id.per);

        // Get the data item for this position
        Firebase_Data_Array user = (Firebase_Data_Array) getItem(position);

        goalTextView.setText(user.getGoal());
        sdateTextView.setText(user.getSdate());
        edateTextView.setText(user.getEdate());
        etcTextView.setText(user.getEtc());
        perTextView.setText(user.getPer());

        return convertView;
    }
}
