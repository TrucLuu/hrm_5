package com.example.fhrm.hrm_system.contants;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ArrayAdapter;
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
    public static void replace (FragmentManager fragmentManager, int layout_id, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(layout_id, fragment)
                .commit();
    }

    public static void mySetText (Dialog dialog, int idInput, TextView textInput, String text){
        textInput = (TextView)dialog.findViewById(idInput);
        textInput.setText(text);
    }

    public static final String [] status ={"Trainee","Intern Ship","Official Staff"};
    public static final String [] position ={"Manager", "Group Leader","Leader","Quality Assurance","Staff"
            ,"Tester","Comtor","Bridge System Engineer"};

    public static void spinnerInterface (Context context, Spinner spiner, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
    }

    public static final List<Department> getDepartmentList(DepartmentDao departmentDao, Context context){
        List<Department> departmentList = null;
        departmentDao = new DepartmentDao(context);
        try {
            departmentList = departmentDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public static final Staff getStaffListbyId(StaffDao staffDao, Context context, int id){
        Staff staffList = null;
        staffDao = new StaffDao(context);
        try {
            staffList = staffDao.getStaff(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}
