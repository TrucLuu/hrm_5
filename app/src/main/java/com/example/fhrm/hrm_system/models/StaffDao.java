package com.example.fhrm.hrm_system.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class StaffDao extends ModelDao<Staff> {

    public StaffDao(Context context) {
        super(context);
    }

    /**
     * CREATING STAFF
     */
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
        rowIdInserted = database.insert(DbConstants.TABLE_STAFF, null, values);
        close();
        return rowIdInserted;
    }

    /**
     * Update
     */
    public int updateStaff(Staff staff) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(DbConstants.STAFF_COLUMN_NAME, staff.getName());
        values.put(DbConstants.STAFF_COLUMN_POB, staff.getPlaceOfBirth());
        values.put(DbConstants.STAFF_COLUMN_DOB, staff.getDateOfBirth());
        values.put(DbConstants.STAFF_COLUMN_PHONE_NUM, staff.getPhoneNumber());
        values.put(DbConstants.STAFF_COLUMN_STATUS_ID, staff.getStatusId());
        values.put(DbConstants.STAFF_COLUMN_POSITION_ID, staff.getPositionId());
        values.put(DbConstants.STAFF_COLUMN_DEPARTMENT_ID, staff.getDepartmentId());
        close();
        return database.update(DbConstants.TABLE_DEPARTMENT, values, DbConstants.STAFF_COLUMN_ID
                + " = ? ", new String[]{String.valueOf(staff.getStaffId())});
    }

    /**
     * Get Single Staff
     */
    public Staff getStaff(int staffId) throws SQLException {
        Cursor cursor = database.query(DbConstants.TABLE_DEPARTMENT,
                new String[]{DbConstants.STAFF_COLUMN_ID,
                        DbConstants.STAFF_COLUMN_NAME,
                        DbConstants.STAFF_COLUMN_DOB,
                        DbConstants.STAFF_COLUMN_PHONE_NUM,
                        DbConstants.STAFF_COLUMN_POB,
                        DbConstants.STAFF_COLUMN_POSITION_ID,
                        DbConstants.STAFF_COLUMN_STATUS_ID,
                        DbConstants.STAFF_COLUMN_DEPARTMENT_ID},
                DbConstants.STAFF_COLUMN_ID + "= ?", new String[]{String.valueOf(staffId)},
                null, null, null, null);
        if (cursor == null)
            cursor.moveToFirst();
        Staff staff = new Staff(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)));
        return staff;
    }
    /**
     * Get all contact
     */
    public List<Staff> getAll() throws SQLException {
        open();
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuerry = "SELECT * FROM " + DbConstants.TABLE_STAFF;
        Cursor cursor = database.rawQuery(selectQuerry, null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor);
                staff.setStaffId(Integer.parseInt(cursor.getString(0)));
                staff.setName(cursor.getString(1));
                staff.setDateOfBirth(cursor.getString(2));
                staff.setPlaceOfBirth(cursor.getString(3));
                staff.setPhoneNumber(cursor.getString(4));
                staff.setDepartmentId(Integer.parseInt(cursor.getString(5)));
                staff.setStatusId(Integer.parseInt(cursor.getString(6)));
                staff.setPositionId(Integer.parseInt(cursor.getString(7)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        close();
        return staffList;
    }
    /**
     * Delete
     */
    public void deleteStaff(Staff staff) throws SQLException {
        database.delete(DbConstants.TABLE_STAFF, DbConstants.STAFF_COLUMN_ID
                + " = ? ", new String[]{String.valueOf(staff.getStaffId())});
        close();
    }
    /**
     * Get Staff by Id Department
     */
    public List<Staff> getStaffById (int index, int pageSize, int pageIndex) throws SQLException {
        open();
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuerry = "SELECT * FROM " + DbConstants.TABLE_STAFF
                +" WHERE "+ DbConstants.TABLE_STAFF +"."
                + DbConstants.STAFF_COLUMN_DEPARTMENT_ID +"="+
                index +" LIMIT " + pageSize + " OFFSET " + pageIndex*pageSize;
        Cursor cursor = database.rawQuery(selectQuerry, null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor);
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        close();
        return staffList;
    }
}
