package com.example.fhrm.hrm_system.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.contants.FragmentControl;
import com.example.fhrm.hrm_system.models.Department;
import com.example.fhrm.hrm_system.models.DepartmentDao;
import com.example.fhrm.hrm_system.models.Staff;
import com.example.fhrm.hrm_system.models.StaffDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by luuhoangtruc on 21/01/2016.
 */
public class AddNewStaffFragment extends Fragment implements View.OnClickListener {
    private Staff staff;

    private EditText inputStaffName, inputPlaceOfBirth, inputPhoneNumber;
    private Spinner spinnerDepartment;
    private Spinner spinnerStatus;
    private Spinner spinnerPosition;
    private Button buttonSave;
    private Button buttonBack;
    private Button buttonDatePicker;
    private TextView textDate;
    private List<Department> departmentList;

    static final int DATE_DIALOG_ID = 999;
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;

    public int statusSave;
    public int departmentSave;
    public int positionSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_staff, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View v) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DepartmentDao departmentDao = new DepartmentDao(getContext());
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < departmentList.size(); i++) {
            arrayList.add(departmentList.get(i).getNameDepartment());
        }
        inputStaffName = (EditText) v.findViewById(R.id.inputStaffName);
        inputPlaceOfBirth = (EditText) v.findViewById(R.id.inputPlaceOfBirth);
        textDate = (TextView) v.findViewById(R.id.inputDateOfBirth);
        textDate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        textDate.setOnClickListener(this);
        inputPhoneNumber = (EditText) v.findViewById(R.id.inputPhoneNumber);
        inputPhoneNumber.setOnClickListener(this);
        /*Spinner Department*/
        spinnerDepartment = (Spinner) v.findViewById(R.id.spinnerDepartmentName);
        ArrayAdapter<String> adapterDepartment = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arrayList);
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapterDepartment);
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                departmentSave = pos + 1;
                Log.i("DEPARTMENT", "onItemSelected: =========>" + departmentSave);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*Spinner Status*/
        spinnerStatus = (Spinner) v.findViewById(R.id.spinnerStatus);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, FragmentControl.status);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionStatus, long id) {
                statusSave = positionStatus + 1;
                Log.i("STATUS", "onItemSelected: =========>" + statusSave);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerStatus.setAdapter(adapterStatus);
        /*Spinner Position*/
        spinnerPosition = (Spinner) v.findViewById(R.id.spinnerPosition);
        ArrayAdapter<String> adapterPosition = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, FragmentControl.position);
        adapterPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosition.setAdapter(adapterPosition);
        spinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionPost, long id) {
                positionSave = positionPost + 1;
                Log.i("POSITION", "onItemSelected: =========>" + positionSave);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonDatePicker = (Button) v.findViewById(R.id.buttonDatePicker);
        buttonDatePicker.setBackgroundResource(R.mipmap.calendar);
        buttonDatePicker.setOnClickListener(this);
        buttonSave = (Button) v.findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        buttonBack = (Button) v.findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(this);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(getContext(), datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            textDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputDateOfBirth:
                onCreateDialog(DATE_DIALOG_ID).show();
                break;
            case R.id.buttonDatePicker:
                onCreateDialog(DATE_DIALOG_ID).show();
                break;

            case R.id.btnBack:
                break;
        }
    }

    public void saveData() {
        String staffName = inputStaffName.getText().toString();
        String staffBirthday = textDate.getText().toString();
        String staffPlaceOfBirth = inputPlaceOfBirth.getText().toString();
        String staffPhone = inputPhoneNumber.getText().toString();
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();
        dialog.setTitle(R.string.titleDialog);
        dialog.show();
        staff = new Staff(getId(), staffName, staffPlaceOfBirth, staffBirthday, staffPhone, departmentSave,
                statusSave, positionSave);
        StaffDao staffDao = new StaffDao(getContext());
        try {
            if (staffDao.insert(staff) > -1) {
                staffDao.insert(staff);
                Toast.makeText(getContext(), getString(R.string.saveSuccess), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.saveUnSuccess), Toast.LENGTH_LONG).show();
        }
    }
}
