package com.example.fhrm.hrm_system.models;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DepartmentDao extends DatabaseHelper implements ModelDao<Department>{

    public DepartmentDao(Context context) {
        super(context);
    }

    @Override
    public long insert(Department departmentId) {
        long rowIdInserted = 0;
        ContentValues values = new ContentValues();
        values.put(DbConstants.DEPARTMENT_COLUMN_NAME, departmentId.getNameDepartment());
        rowIdInserted = sDatabase.insert(DbConstants.TABLE_DEPARTMENT, null, values);
        return rowIdInserted;
    }
}
