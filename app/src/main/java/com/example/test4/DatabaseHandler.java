package com.example.test4;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.

    public static final String DB_NAME = "database.db";


    public static final int DB_VERSION = 1;


    // Refugee
    public static final String REFUGEE_TABLE_NAME = "refugee";


    public static final String REFUGEE_COLUMN_ID = "id";

    public static final String REFUGEE_COLUMN_USERNAME = "username";

    public static final String REFUGEE_COLUMN_PASSWORD = "password";

    public static final String REFUGEE_COLUMN_NUMBER = "number";

    public static final String REFUGEE_COLUMN_LANGUAGE = "language";


    // Volunteer table
    public static final String VOLUNTEER_TABLE_NAME = "volunteer";

    public static final String VOLUNTEER_COLUMN_ID = "id";

    public static final String VOLUNTEER_COLUMN_USERNAME = "username";

    public static final String VOLUNTEER_COLUMN_PASSWORD = "password";

    public static final String VOLUNTEER_COLUMN_NUMBER = "number";

    public static final String VOLUNTEER_COLUMN_LANGUAGE = "language";

    public static final String VOLUNTEER_COLUMN_STATUS = "status";


    // Notes
    public static final String TABLE_NAME_NOTES = "notes";

    public static final String ID_COL_NOTES = "id";

    public static final String COL_TEXT = "text";


    // creating a constructor for our database handler.
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create refugee table
        db.execSQL("CREATE TABLE " + REFUGEE_TABLE_NAME + " (" +
                REFUGEE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                REFUGEE_COLUMN_USERNAME + " TEXT, " +
                REFUGEE_COLUMN_PASSWORD + " TEXT, " +
                REFUGEE_COLUMN_NUMBER + " TEXT, " +
                REFUGEE_COLUMN_LANGUAGE + " TEXT)");;

        // Create volunteer table
        db.execSQL("CREATE TABLE " + VOLUNTEER_TABLE_NAME + " (" +
                VOLUNTEER_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                VOLUNTEER_COLUMN_USERNAME + " TEXT, " +
                VOLUNTEER_COLUMN_PASSWORD + " TEXT, " +
                VOLUNTEER_COLUMN_NUMBER + " TEXT, " +
                VOLUNTEER_COLUMN_LANGUAGE + " TEXT, " +
                VOLUNTEER_COLUMN_STATUS + " TEXT)");;

        // cREATE NOTES TABLE
        db.execSQL("CREATE TABLE " + TABLE_NAME_NOTES + " ("
                + ID_COL_NOTES + " INTEGER PRIMARY KEY,"
                + COL_TEXT + " TEXT, "
                + " FOREIGN KEY ("+REFUGEE_COLUMN_ID+") REFERENCES "+REFUGEE_TABLE_NAME+"("+REFUGEE_COLUMN_ID+"));");

    }

    // this method is use to add new table to our sqlite database.
    public void addRefugeeTable(String username, String password, String telephone, String language) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase(); // creating a reference to the database

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(REFUGEE_COLUMN_USERNAME, username);
        values.put(REFUGEE_COLUMN_PASSWORD, password);
        values.put(REFUGEE_COLUMN_NUMBER, telephone);
        values.put(REFUGEE_COLUMN_LANGUAGE, language);

        // after adding all values we are passing
        // content values to our table.
        db.insert(REFUGEE_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public void addVolunteerTable(String username, String password, String telephone, String language, String status) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase(); // creating a reference to the database

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VOLUNTEER_COLUMN_USERNAME, username);
        values.put(VOLUNTEER_COLUMN_PASSWORD, password);
        values.put(VOLUNTEER_COLUMN_NUMBER, telephone);
        values.put(VOLUNTEER_COLUMN_LANGUAGE, language);
        values.put(VOLUNTEER_COLUMN_STATUS, status);

        // after adding all values we are passing
        // content values to our table.
        db.insert(REFUGEE_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public void AddNotesTable(String text) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase(); // creating a reference to the database

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        values.put(COL_TEXT, text);

        db.insert(TABLE_NAME_NOTES, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // In order to update the table, we need to delete it and create a new upgraded one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + REFUGEE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOLUNTEER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTES);

        // calling the onCreate method that creates the database
        onCreate(db);
    }
}
