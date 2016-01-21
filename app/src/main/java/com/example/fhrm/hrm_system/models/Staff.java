package com.example.fhrm.hrm_system.models;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class Staff {

    private int staffId;
    private String name;
    private String dateOfBirth;
    private String placeOfBirth;
    private String phoneNumber;
    private int departmentId;
    private int statusId;
    private int positionId;

    public Staff() {
    }

    public Staff(int staffId, String name, String date_of_birth, String birth_place, String phone_number, int dept_id, int status_id, int position_id) {
        this.staffId = staffId;
        this.name = name;
        this.dateOfBirth = date_of_birth;
        this.placeOfBirth = birth_place;
        this.phoneNumber = phone_number;
        this.departmentId = dept_id;
        this.statusId = status_id;
        this.positionId = position_id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
