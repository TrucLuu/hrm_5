package com.example.fhrm.hrm_system.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DatabaseHelper {

    // ALL STATIC VARIABLE
    /**
     * Database Version
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database Name
     */
    private static final String DATABASE_NAME = "hrm.db";
    /**
     * department table create statement
     */
    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + DbConstants.TABLE_DEPARTMENT
            + "(" + DbConstants.DEPARTMENT_COLUMN_ID + " INTEGER PRIMARY KEY, "
                  + DbConstants.DEPARTMENT_COLUMN_NAME + " " + "TEXT" + ")";
    /**
     * staff table create statement
     */
    private static final String CREATE_TABLE_STAFF = "CREATE TABLE " + DbConstants.TABLE_STAFF + "("
            + DbConstants.STAFF_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + DbConstants.STAFF_COLUMN_NAME + " TEXT, "
            + DbConstants.STAFF_COLUMN_POB + " TEXT, "
            + DbConstants.STAFF_COLUMN_DOB + " TEXT, "
            + DbConstants.STAFF_COLUMN_PHONE_NUM + " TEXT, "
            + DbConstants.STAFF_COLUMN_STATUS_ID + " INTEGER, "
            + DbConstants.STAFF_COLUMN_POSITION_ID + " INTEGER, "
            + DbConstants.STAFF_COLUMN_DEPARTMENT_ID + " REFERENCES, "
            + DbConstants.TABLE_DEPARTMENT + "(" + DbConstants.DEPARTMENT_COLUMN_ID + "), FOREIGN" + ")";

    protected static Context sContext;
    public static SQLiteDatabase sDatabase;
    protected OpenHelper mOpenHelper;

    public DatabaseHelper(Context context) {
        DatabaseHelper.sContext = context;
    }

    public DatabaseHelper open() throws SQLException {
        mOpenHelper = new OpenHelper(sContext);
        sDatabase = mOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mOpenHelper.close();
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_DEPARTMENT);
            db.execSQL(CREATE_TABLE_STAFF);
        }

        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_DEPARTMENT);
            db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_STAFF);
            onCreate(db);
        }
    }
}
