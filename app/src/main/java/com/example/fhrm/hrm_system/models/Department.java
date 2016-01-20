package com.example.fhrm.hrm_system.models;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class Department {
    private int departmentId;
    private String nameDepartment;

    public Department () {

    }

    public Department (String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Department (String nameDepartment, int departmentId) {
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
