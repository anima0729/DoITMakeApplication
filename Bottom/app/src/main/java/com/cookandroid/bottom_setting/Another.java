package com.cookandroid.bottom_setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class Another extends Fragment implements View.OnClickListener{
    // ProfileBox 텍스트 뷰
    TextView ProfileBox;

    public static Another newInstance() {
        return new Another();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // PreferenceManager를 이용한 Profile 정보 불러오기
        String nickname = PreferenceManager.getString(getContext(), "nickname");
        String gender = PreferenceManager.getString(getContext(), "gender");
        View fv = inflater.inflate(R.layout.another, container, false);

        // ProfileBox에 Profile 정보 표시
        ProfileBox = fv.findViewById(R.id.textView);
        ProfileBox.setText(nickname + " / " + gender);

        return fv;
    }

    @Override
    public void onClick(View v) {

    }

    private void setChildFragment(Fragment child) {
        FragmentTransaction userFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            userFt.replace(R.id.User, child);
            userFt.addToBackStack(null);
            userFt.commit();
        }
        FragmentTransaction systemFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            systemFt.replace(R.id.System, child);
            systemFt.addToBackStack(null);
            systemFt.commit();
        }
        FragmentTransaction developerFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            developerFt.replace(R.id.Developer, child);
            developerFt.addToBackStack(null);
            developerFt.commit();
        }

    }
}