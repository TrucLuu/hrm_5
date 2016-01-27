package com.example.fhrm.hrm_system.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.adapter.ArrayAdapterDepartment;
import com.example.fhrm.hrm_system.adapter.ArrayAdapterStaff;
import com.example.fhrm.hrm_system.contants.EnumClass;
import com.example.fhrm.hrm_system.contants.FragmentControl;
import com.example.fhrm.hrm_system.models.Department;
import com.example.fhrm.hrm_system.models.DepartmentDao;
import com.example.fhrm.hrm_system.models.Staff;
import com.example.fhrm.hrm_system.models.StaffDao;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.fhrm.hrm_system.contants.EnumClass.PositionCode.fromPositionValue;
import static com.example.fhrm.hrm_system.contants.EnumClass.StatusCode.fromValue;
import static com.example.fhrm.hrm_system.contants.FragmentControl.mySetText;

/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class DepartmentFragment extends Fragment {
    private static final String TAG = DepartmentFragment.class.getSimpleName();
    private String value;
    private String nameDepartment;
    public ListView listStaff;
    private List<Staff> staffList;
    private ArrayAdapterStaff arrayStaff;
    private int pageIndex = 0;
    private int pageSize = 30;
    private ProgressDialog mProgressDialog;
    private boolean isFinished;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View v) {
        Bundle bundle = this.getArguments();
        value = bundle.getString("id_department");
        nameDepartment = bundle.getString("name_department");
        listStaff = (ListView) v.findViewById(R.id.lvStaff);
        StaffDao staffDao = new StaffDao(getContext());
        try {
            staffList = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        arrayStaff = new ArrayAdapterStaff(getContext(), android.R.layout.simple_list_item_1, staffList);
        listStaff.setAdapter(arrayStaff);
        getActivity().setTitle(nameDepartment);
        listStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Dialog dialogStaffInfor = new Dialog(view.getContext());
            dialogStaffInfor.setContentView(R.layout.fragment_information_staff);
            dialogStaffInfor.setTitle(R.string.titleDialogStaff);
            TextView textSomeField = null;
            mySetText(dialogStaffInfor, R.id.textStaffName, textSomeField, staffList.get(position).getName());
            mySetText(dialogStaffInfor, R.id.textPlaceOfBirth, textSomeField, staffList.get(position).getPlaceOfBirth());
            mySetText(dialogStaffInfor, R.id.textDateOfBirth, textSomeField, staffList.get(position).getDateOfBirth());
            mySetText(dialogStaffInfor, R.id.textPhoneNumber, textSomeField, staffList.get(position).getPhoneNumber());
            mySetText(dialogStaffInfor, R.id.textDepartment, textSomeField, nameDepartment);
            mySetText(dialogStaffInfor, R.id.textStatus, textSomeField, fromValue(staffList.get(position).getStatusId()).getText());
            mySetText(dialogStaffInfor, R.id.textPosition, textSomeField, fromPositionValue(staffList.get(position).getPositionId()).getText());
            dialogStaffInfor.show();
            }
        });

        listStaff.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = listStaff.getCount();
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listStaff.getLastVisiblePosition() >= count - threshold) {
                        if (isFinished) return;
                        LoadMoreDataTask asyncTask = new LoadMoreDataTask();
                        asyncTask.execute();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public static DepartmentFragment newInstance(int index, String name) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id_department", String.valueOf(index));
        bundle.putString("name_department", name);
        fragment.setArguments(bundle);
        return fragment;
    }

    class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setTitle(R.string.titleDialog);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            StaffDao staffDao = new StaffDao(getContext());
            pageIndex++;
            try {
                if(staffList == null ) {
                    staffList = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
                    return null;
                }
                List<Staff> tmp = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
                if(tmp.size() == 0)
                    isFinished = true;
                staffList.addAll(tmp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (arrayStaff == null) {
                arrayStaff = new ArrayAdapterStaff(getContext(), android.R.layout.simple_list_item_1, staffList);
                listStaff.setAdapter(arrayStaff);
            } else {
                arrayStaff.notifyDataSetChanged();
            }
            mProgressDialog.dismiss();
            super.onPostExecute(result);
        }
    }
}
