package com.example.fhrm.hrm_system.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DepartmentDao extends ModelDao<Department> {

    public DepartmentDao(Context context) {
        super(context);
    }

    public long insert(Department department) throws SQLException {
        open();
        long rowInsert = 0;
        ContentValues values = new ContentValues();
        values.put(DbConstants.DEPARTMENT_COLUMN_NAME, department.getNameDepartment());
        rowInsert = database.insert(DbConstants.TABLE_DEPARTMENT, null, values);
        close();
        return rowInsert;
    }

    /**
     * Update
     */
    public int updateDepartment(Department department) throws SQLException {
        open();
        ContentValues cValues = new ContentValues();
        cValues.put(DbConstants.DEPARTMENT_COLUMN_NAME, department.getNameDepartment());
        close();
        return database.update(DbConstants.TABLE_DEPARTMENT, cValues, DbConstants.DEPARTMENT_COLUMN_ID
                + " = ? ", new String[]{String.valueOf(department.getDepartmentId())});
    }

    /**
     * Get Single Department
     */
    public Department getDepartment(int departmentId) throws SQLException {
        Cursor cursor = database.query(DbConstants.TABLE_DEPARTMENT,
            new String[]{DbConstants.DEPARTMENT_COLUMN_ID, DbConstants.DEPARTMENT_COLUMN_NAME},
            DbConstants.DEPARTMENT_COLUMN_ID + "= ?", new String[]{String.valueOf(departmentId)},
            null, null, null, null);
        if (cursor == null)
            cursor.moveToFirst();
        Department department = new Department(cursor);
        return department;
    }

    /**
     * Get all contact
     */
    public List<Department> getAll() throws SQLException {
        open();
        List<Department> departmentList = new ArrayList<Department>();
        String selectQuerry = "SELECT * FROM " + DbConstants.TABLE_DEPARTMENT;
        Cursor cursor = database.rawQuery(selectQuerry,null);
        if (cursor.moveToFirst()) {
            do {
                Department department = new Department(cursor);
                departmentList.add(department);
            } while (cursor.moveToNext());
        }
        close();
        return departmentList;
    }
    /**
     * Delete
     */
    public void deleteDepartment(Department department) throws SQLException {
        database.delete(DbConstants.TABLE_DEPARTMENT, DbConstants.DEPARTMENT_COLUMN_ID
                + " = ? ", new String[]{String.valueOf(department.getDepartmentId())});
        close();
    }
}
