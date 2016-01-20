package com.example.fhrm.hrm_system.models;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DbConstants {
    /**
     * Staff Table Name
     */
    public static final String TABLE_STAFF = "staffs";
    public static final String TABLE_DEPARTMENT = "department";

    /**
     * Department table column names
     */
    public static final String DEPARTMENT_COLUMN_ID = "departmentId";
    public static final String DEPARTMENT_COLUMN_NAME = "nameDepartment";

    /**
     * Staff Table Columns names
     */
    public static final String STAFF_COLUMN_ID = "name";
    public static final String STAFF_COLUMN_NAME = "name";
    public static final String STAFF_COLUMN_POB = "placeOfBirth";
    public static final String STAFF_COLUMN_DOB = "dateOfBirth";
    public static final String STAFF_COLUMN_PHONE_NUM = "phoneNumber";
    public static final String STAFF_COLUMN_DEPARTMENT_ID = DEPARTMENT_COLUMN_ID;
    public static final String STAFF_COLUMN_STATUS_ID = "statusId";
    public static final String STAFF_COLUMN_POSITION_ID = "positionId";
}
