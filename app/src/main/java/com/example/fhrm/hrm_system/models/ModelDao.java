package com.example.fhrm.hrm_system.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public abstract class ModelDao<T> {
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ModelDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
