package com.example.fhrm.hrm_system.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.adapter.ArrayAdapterDepartment;
import com.example.fhrm.hrm_system.contants.FragmentControl;
import com.example.fhrm.hrm_system.models.Department;
import com.example.fhrm.hrm_system.models.DepartmentDao;

import java.sql.SQLException;
import java.util.List;

import static com.example.fhrm.hrm_system.contants.FragmentControl.replace;


/**
 * Created by luuhoangtruc on 21/01/2016.
 */
public class HomeFragment extends Fragment {
    public ListView listDepartment;
    private List<Department> departmentList;
    private ArrayAdapterDepartment arrayDepartment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void initialize(View view) {
        listDepartment = (ListView) view.findViewById(R.id.lvDepartment);
        DepartmentDao departmentDao = new DepartmentDao(getContext());
        departmentList = FragmentControl.getDepartmentList(departmentDao, getContext());
        arrayDepartment = new ArrayAdapterDepartment(getContext(), android.R.layout.simple_list_item_1, departmentList);
        listDepartment.setAdapter(arrayDepartment);
        listDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                replace(getFragmentManager(), R.id.flContent,
                        DepartmentFragment.newInstance(departmentList.get(position).getDepartmentId(),
                                departmentList.get(position).getNameDepartment()));
            }
        });
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.app_name));
    }
}
