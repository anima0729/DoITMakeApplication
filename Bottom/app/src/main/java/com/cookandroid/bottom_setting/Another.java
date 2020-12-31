package com.cookandroid.bottom_setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Another extends Fragment implements View.OnClickListener{
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
        View fv = inflater.inflate(R.layout.another, container, false);

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