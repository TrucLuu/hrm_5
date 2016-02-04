package com.example.fhrm.hrm_system.contants;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.models.Department;
import com.example.fhrm.hrm_system.models.DepartmentDao;
import com.example.fhrm.hrm_system.models.Staff;
import com.example.fhrm.hrm_system.models.StaffDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class FragmentControl {
    public static void replace(FragmentManager fragmentManager, int layout_id, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(layout_id, fragment)
                .commit();
    }

    public static void mySetTextDialog(Dialog dialog, int idInput, TextView textInput, String text) {
        textInput = (TextView) dialog.findViewById(idInput);
        textInput.setText(text);
    }

    public static void mySetTextfromEditText(View v, int idInput, EditText editText, String text) {
        editText = (EditText) v.findViewById(idInput);
        editText.setText(text);
    }

    public static void spinnerInterface(Context context, Spinner spiner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(context, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
    }

    public static void spinnerInterfaceList(Context context, Spinner spiner, List<String> string) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, string);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
    }

    public static List<Department> getDepartmentList(DepartmentDao departmentDao, Context context) {
        List<Department> departmentList = null;
        departmentDao = new DepartmentDao(context);
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public static Staff getStaffListbyId(Context context, int id) {
        Staff staffList = null;
        StaffDao staffDao = new StaffDao(context);
        try {
            staffList = staffDao.getStaff(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public static boolean validates(String strError, EditText... editTexts) {
        if (editTexts == null || editTexts.length == 0) {
            return false;
        }
        for (EditText edt : editTexts) {
            if (!TextUtils.isEmpty(edt.getText().toString())) {
                continue;
            }
            edt.setError(strError);
            return false;
        }
        return true;
    }

    public static void clearFocus(Context context, EditText... editText) {
        for (EditText edt : editText) {
            InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(edt.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void clearFocusSwitchFragment(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
