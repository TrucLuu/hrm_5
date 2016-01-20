package com.example.fhrm.hrm_system.models;

import android.content.ContentValues;
import android.content.Context;
import java.sql.SQLException;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class StaffDao extends DatabaseHelper implements ModelDao<Staff>{

    public StaffDao(Context context) {
        super(context);
    }

    /** CREATING STAFF */
    public long insert(Staff staff) throws SQLException {
        open();
        long rowIdInserted = 0;
        ContentValues values = new ContentValues();
        values.put(DbConstants.STAFF_COLUMN_NAME, staff.getName());
        values.put(DbConstants.STAFF_COLUMN_POB, staff.getPlaceOfBirth());
        values.put(DbConstants.STAFF_COLUMN_DOB, staff.getDateOfBirth());
        values.put(DbConstants.STAFF_COLUMN_PHONE_NUM, staff.getPhoneNumber());
        values.put(DbConstants.STAFF_COLUMN_STATUS_ID, staff.getStatusId());
        values.put(DbConstants.STAFF_COLUMN_POSITION_ID, staff.getPositionId());
        values.put(DbConstants.STAFF_COLUMN_DEPARTMENT_ID, staff.getDepartmentId());
        rowIdInserted  = sDatabase.insert(DbConstants.TABLE_STAFF, null, values);
        close();
        return rowIdInserted;
    }
}
