package com.example.fhrm.hrm_system.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.adapter.ArrayAdapterStaff;
import com.example.fhrm.hrm_system.models.Staff;
import com.example.fhrm.hrm_system.models.StaffDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import static com.example.fhrm.hrm_system.contants.EnumClass.PositionCode.fromPositionValue;
import static com.example.fhrm.hrm_system.contants.EnumClass.StatusCode.fromValue;
import static com.example.fhrm.hrm_system.contants.FragmentControl.mySetTextDialog;
import static com.example.fhrm.hrm_system.contants.FragmentControl.replace;

/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class DepartmentFragment extends Fragment {
    private static final String TAG = DepartmentFragment.class.getSimpleName();
    private String value;
    private String nameDepartment;
    public ListView listStaff;
    private List<Staff> staffList;
    private List<Staff> staffArrayList;
    private ArrayAdapterStaff arrayStaff;
    private int pageIndex = 0;
    private int pageSize = 30;
    private ProgressDialog mProgressDialog;
    private boolean isFinished;
    private EditText editTextSearch;
    private TextView textSomeField;
    private Dialog dialogStaffInfor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        initialize(view);
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void initialize(View v) {
        Bundle bundle = this.getArguments();
        value = bundle.getString("id_department");
        nameDepartment = bundle.getString("name_department");
        listStaff = (ListView) v.findViewById(R.id.lvStaff);
        editTextSearch = (EditText) v.findViewById(R.id.searchView);
        StaffDao staffDao = new StaffDao(getContext());
        try {
            staffList = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
            staffArrayList = staffDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textSearch = editTextSearch.getText().toString().toLowerCase(Locale.getDefault());
                filter(textSearch);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        arrayStaff = new ArrayAdapterStaff(getContext(), android.R.layout.simple_list_item_1, staffList);
        listStaff.setAdapter(arrayStaff);
        getActivity().setTitle(nameDepartment);
        listStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                dialogStaffInfor = new Dialog(view.getContext());
                dialogStaffInfor.setContentView(R.layout.fragment_information_staff);
                dialogStaffInfor.setTitle(R.string.titleDialogStaff);
                textSomeField = null;
                Staff mPosition = staffList.get(pos);
                setData(mPosition);
                dialogStaffInfor.show();
            }
        });

        listStaff.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                replace(getFragmentManager(), R.id.flContent,
                        EditStaffFragment.newInstance(staffList.get(position).getStaffId()));
                return false;
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
                if (staffList == null) {
                    staffList = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
                    return null;
                }
                List<Staff> tmp = staffDao.getStaffById(Integer.valueOf(value), pageSize, pageIndex);
                if (tmp.size() == 0)
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
                arrayStaff.notifyDataSetChanged();
            } else {
                arrayStaff.notifyDataSetInvalidated();
            }
            mProgressDialog.dismiss();
            super.onPostExecute(result);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (charText.length() == 0) {
            staffList.clear();
            staffList.addAll(staffArrayList);
            arrayStaff.notifyDataSetChanged();
        } else {
            staffList.clear();
            Locale localeDefault = Locale.getDefault();
            for (Staff wp : staffArrayList) {
                if (wp.getName().toLowerCase(localeDefault).contains(charText)) {
                    staffList.add(wp);
                }
            }
            arrayStaff.notifyDataSetInvalidated();
        }
    }

    public void setData(Staff staff){
        mySetTextDialog(dialogStaffInfor, R.id.textStaffName, textSomeField, staff.getName());
        mySetTextDialog(dialogStaffInfor, R.id.textPlaceOfBirth, textSomeField, staff.getPlaceOfBirth());
        mySetTextDialog(dialogStaffInfor, R.id.textDateOfBirth, textSomeField, staff.getDateOfBirth());
        mySetTextDialog(dialogStaffInfor, R.id.textPhoneNumber, textSomeField, staff.getPhoneNumber());
        mySetTextDialog(dialogStaffInfor, R.id.textDepartment, textSomeField, nameDepartment);
        mySetTextDialog(dialogStaffInfor, R.id.textStatus, textSomeField, fromValue(staff.getStatusId()).getText());
        mySetTextDialog(dialogStaffInfor, R.id.textPosition, textSomeField, fromPositionValue(staff.getPositionId()).getText());
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(nameDepartment);
    }
}
