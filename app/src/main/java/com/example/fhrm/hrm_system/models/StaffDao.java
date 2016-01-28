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
        long rowIdInserted = -1;
        ContentValues values = new ContentValues();
        values.put(DbConstants.STAFF_COLUMN_NAME, staff.getName());
        values.put(DbConstants.STAFF_COLUMN_POB, staff.getPlaceOfBirth());
        values.put(DbConstants.STAFF_COLUMN_DOB, staff.getDateOfBirth());
        values.put(DbConstants.STAFF_COLUMN_PHONE_NUM, staff.getPhoneNumber());
        values.put(DbConstants.STAFF_COLUMN_DEPARTMENT_ID, staff.getDepartmentId());
        values.put(DbConstants.STAFF_COLUMN_STATUS_ID, staff.getStatusId());
        values.put(DbConstants.STAFF_COLUMN_POSITION_ID, staff.getPositionId());
        rowIdInserted = database.insert(DbConstants.TABLE_STAFF, null, values);
        close();
        return rowIdInserted;
    }

    /**
     * Update
     */
    public int updateStaff(Staff staff) throws SQLException {
        open();
        int rowInsert = 0;
        ContentValues values;
        values= new ContentValues();
        values.put(DbConstants.STAFF_COLUMN_NAME, staff.getName());
        values.put(DbConstants.STAFF_COLUMN_POB, staff.getPlaceOfBirth());
        values.put(DbConstants.STAFF_COLUMN_DOB, staff.getDateOfBirth());
        values.put(DbConstants.STAFF_COLUMN_PHONE_NUM, staff.getPhoneNumber());
        values.put(DbConstants.STAFF_COLUMN_STATUS_ID, staff.getStatusId());
        values.put(DbConstants.STAFF_COLUMN_POSITION_ID, staff.getPositionId());
        values.put(DbConstants.STAFF_COLUMN_DEPARTMENT_ID, staff.getDepartmentId());
        String[] whereArgs = {String.valueOf(staff.getStaffId())};
        rowInsert = database.update(DbConstants.TABLE_STAFF, values, DbConstants.STAFF_COLUMN_ID + " = ?", whereArgs);
        close();
        return rowInsert;
    }

    /**
     * Get Single Staff
     */
    public Staff getStaff(int staffId) throws SQLException {
        open();
        Staff staff = null;
        String selection = DbConstants.STAFF_COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(staffId)};
        Cursor cursor = database.query(DbConstants.TABLE_STAFF,
                new String[]{DbConstants.STAFF_COLUMN_ID,
                        DbConstants.STAFF_COLUMN_NAME,
                        DbConstants.STAFF_COLUMN_POB,
                        DbConstants.STAFF_COLUMN_DOB,
                        DbConstants.STAFF_COLUMN_PHONE_NUM,
                        DbConstants.STAFF_COLUMN_DEPARTMENT_ID,
                        DbConstants.STAFF_COLUMN_STATUS_ID,
                        DbConstants.STAFF_COLUMN_POSITION_ID},
                selection, selectionArgs,
                null, null, "1");
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_NAME));
            String placeOfBirth = cursor.getString(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_POB));
            String dateOfBirth = cursor.getString(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_DOB));
            String phone = cursor.getString(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_PHONE_NUM));
            int departmentId = cursor.getInt(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_DEPARTMENT_ID));
            int statusId = cursor.getInt(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_STATUS_ID));
            int positionId = cursor.getInt(cursor.getColumnIndex(DbConstants.STAFF_COLUMN_POSITION_ID));
            staff = new Staff(id, name, placeOfBirth, dateOfBirth, phone, departmentId, statusId, positionId);
            staff.setStatusId(id);
        }
        close();
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
        open();
        database.delete(DbConstants.TABLE_STAFF, DbConstants.STAFF_COLUMN_ID
                + " = ? ", new String[]{String.valueOf(staff.getStaffId())});
        close();
    }

    /**
     * Get Staff by Id Department
     */
    public List<Staff> getStaffById(int index, int pageSize, int pageIndex) throws SQLException {
        open();
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuerry = "SELECT * FROM " + DbConstants.TABLE_STAFF
                + " WHERE " + DbConstants.TABLE_STAFF + "."
                + DbConstants.STAFF_COLUMN_DEPARTMENT_ID + "=" +
                index + " LIMIT " + pageSize + " OFFSET " + pageIndex * pageSize;
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
