package com.cookandroid.bottom_setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Statistics extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.statistics, container, false);

        // 그래프 종류 설정
        LineChart linechart = (LineChart) view.findViewById(R.id.chart);

        // 그래프에 넣을 점 설정
        // 후에는 데이터베이스에서 받아온 정보를 이용하여 추가할 예정
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 1));
        entries.add(new Entry(1, 3));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 1));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 0));
        entries.add(new Entry(6, 2));

        // 세로축 설정
        LineDataSet dataset = new LineDataSet(entries, "달성 목표 수");

        LineData data = new LineData(dataset);
        linechart.setData(data);
        linechart.setDrawGridBackground(false);
        // 애니메이션 설정
        // 설정시 그래프가 한번에 나타나는 것이 아니라 창 이동시 그래프의 선이 제자리 찾아가면서 나타남
        linechart.animateY(1500);

        return view;
    }
}