package com.example.fhrm.hrm_system.models;

import android.database.Cursor;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class Department {
    private int departmentId;
    private String nameDepartment;

    public Department(Cursor cursor) {
        this.departmentId =cursor.getInt(cursor.getColumnIndexOrThrow("departmentId"));
        this.nameDepartment =cursor.getString(cursor.getColumnIndexOrThrow("nameDepartment"));
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Department(int departmentId, String nameDepartment) {
        this.departmentId = departmentId;
        this.nameDepartment = nameDepartment;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
