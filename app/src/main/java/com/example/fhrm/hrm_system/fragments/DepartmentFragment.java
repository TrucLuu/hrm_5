package com.example.fhrm.hrm_system.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;

/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class DepartmentFragment extends Fragment {
    private String value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View v) {
        Bundle bundle = this.getArguments();
        value = bundle.getString("id_department");
    }

    public static DepartmentFragment newInstance (int index) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id_department", String.valueOf(index));
        fragment.setArguments(bundle);
        return fragment;
    }
}
