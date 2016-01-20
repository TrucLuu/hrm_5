package com.example.fhrm.hrm_system.models;

import android.content.ContentValues;
import android.content.Context;

import java.sql.SQLException;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DepartmentDao extends ModelDao<Department>{

    public DepartmentDao(Context context) {
        super(context);
    }

    public long insert(Department departmentId) throws SQLException {
        open();
        long rowInsert = 0;
        ContentValues values = new ContentValues();
        values.put(DbConstants.DEPARTMENT_COLUMN_NAME, departmentId.getNameDepartment());
        rowInsert = database.insert(DbConstants.TABLE_DEPARTMENT, null, values);
        close();
        return rowInsert;
    }
}
