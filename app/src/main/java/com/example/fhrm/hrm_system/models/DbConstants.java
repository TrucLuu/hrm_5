package com.example.fhrm.hrm_system.models;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

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
    public static final String STAFF_COLUMN_ID = "staffId";
    public static final String STAFF_COLUMN_NAME = "name";
    public static final String STAFF_COLUMN_POB = "placeOfBirth";
    public static final String STAFF_COLUMN_DOB = "dateOfBirth";
    public static final String STAFF_COLUMN_PHONE_NUM = "phoneNumber";
    public static final String STAFF_COLUMN_DEPARTMENT_ID = DEPARTMENT_COLUMN_ID;
    public static final String STAFF_COLUMN_STATUS_ID = "statusId";
    public static final String STAFF_COLUMN_POSITION_ID = "positionId";

    public static String getStringbyFile(Context context, String fileDir) {
        String str = null;
        try {
            InputStream inputStream = context.getAssets().open(fileDir);
            int length = inputStream.available();
            byte[] data = new byte[length];
            inputStream.read(data);
            str = new String(data).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
