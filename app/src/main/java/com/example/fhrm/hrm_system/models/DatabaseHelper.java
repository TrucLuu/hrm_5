package com.example.fhrm.hrm_system.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // ALL STATIC VARIABLE
    /**
     * Database Version
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database Name
     */
    private static final String DATABASE_NAME = "hrm.db";
    public static final String DEMO_PATH_INSERT = "sql/SchemeQueryInsert.sql";
    /**
     * department table create statement
     */
    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + DbConstants.TABLE_DEPARTMENT
            + "(" + DbConstants.DEPARTMENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbConstants.DEPARTMENT_COLUMN_NAME + " " + "TEXT" + ")";
    /**
     * staff table create statement
     */
    private static final String CREATE_TABLE_STAFF = "CREATE TABLE " + DbConstants.TABLE_STAFF + "("
            + DbConstants.STAFF_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbConstants.STAFF_COLUMN_NAME + " TEXT , "
            + DbConstants.STAFF_COLUMN_POB + " TEXT , "
            + DbConstants.STAFF_COLUMN_DOB + " TEXT , "
            + DbConstants.STAFF_COLUMN_PHONE_NUM + " TEXT , "
            + DbConstants.STAFF_COLUMN_STATUS_ID + " INTEGER , "
            + DbConstants.STAFF_COLUMN_POSITION_ID + " INTEGER , "
            + DbConstants.STAFF_COLUMN_DEPARTMENT_ID + " INTEGER , "
            + "FOREIGN KEY (" + DbConstants.STAFF_COLUMN_DEPARTMENT_ID + ") REFERENCES "
            + DbConstants.TABLE_DEPARTMENT + "(" + DbConstants.DEPARTMENT_COLUMN_ID + "))";

    Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_STAFF);
        initDatabase(db, DEMO_PATH_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_STAFF);
        onCreate(db);
    }

    public void initDatabase(SQLiteDatabase database, String path) {
        String script = DbConstants.getStringbyFile(this.mContext, path);
        String[] createScript = script.split(";");
        for (int i = 0; i < createScript.length; i++) {
            String sqlStatement = createScript[i].trim();
            if (sqlStatement.length() > 0) {
                database.execSQL(sqlStatement + ";");
            }
        }
    }
}
