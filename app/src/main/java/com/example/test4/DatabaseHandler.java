package com.example.test4;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;

    // Refugee table
    public static final String REFUGEE_TABLE_NAME = "refugee";
    public static final String REFUGEE_COLUMN_ID = "id";
    public static final String REFUGEE_COLUMN_USERNAME = "username";
    public static final String REFUGEE_COLUMN_PASSWORD = "password";
    public static final String REFUGEE_COLUMN_NUMBER = "number";
    public static final String REFUGEE_COLUMN_LANGUAGE = "language";
    public static final String REFUGEE_COLUMN_NOTES = "notes";

    // Volunteer table
    public static final String VOLUNTEER_TABLE_NAME = "volunteer";
    public static final String VOLUNTEER_COLUMN_ID = "id";
    public static final String VOLUNTEER_COLUMN_USERNAME = "username";
    public static final String VOLUNTEER_COLUMN_PASSWORD = "password";
    public static final String VOLUNTEER_COLUMN_NUMBER = "number";
    public static final String VOLUNTEER_COLUMN_LANGUAGE = "language";
    public static final String VOLUNTEER_COLUMN_STATUS = "status";

    // Notes table
    //public static final String NOTES_TABLE_NAME = "notes";
    //public static final String NOTES_COLUMN_ID = "id";

    //public static final String NOTES = "text22";

    //public static final String NOTES_REFUGEE_ID = "refugee_id";

    // creating a constructor for our database handler.
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create refugee table
        db.execSQL("CREATE TABLE " + REFUGEE_TABLE_NAME + " (" +
                REFUGEE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REFUGEE_COLUMN_USERNAME + " TEXT, " +
                REFUGEE_COLUMN_PASSWORD + " TEXT, " +
                REFUGEE_COLUMN_NUMBER + " TEXT, " +
                REFUGEE_COLUMN_LANGUAGE + " TEXT," +
                REFUGEE_COLUMN_NOTES + " TEXT)");

        // Create volunteer table
        db.execSQL("CREATE TABLE " + VOLUNTEER_TABLE_NAME + " (" +
                VOLUNTEER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VOLUNTEER_COLUMN_USERNAME + " TEXT, " +
                VOLUNTEER_COLUMN_PASSWORD + " TEXT, " +
                VOLUNTEER_COLUMN_NUMBER + " TEXT, " +
                VOLUNTEER_COLUMN_LANGUAGE + " TEXT, " +
                VOLUNTEER_COLUMN_STATUS + " TEXT)");

        // Create notes table
       // db.execSQL("CREATE TABLE " + NOTES_TABLE_NAME + " (" +
        //        NOTES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //        NOTES + " TEXT, " +
        //        NOTES_REFUGEE_ID + " INTEGER, " +
        //        "FOREIGN KEY (" + NOTES_REFUGEE_ID + ") REFERENCES " +
        //        REFUGEE_TABLE_NAME + "(" + REFUGEE_COLUMN_ID + "))");
    }

    // this method is use to add new table to our sqlite database.
    public void addRefugeeTable(String username, String password, String telephone, ArrayList<String> selectedLanguagesList) {
        String languages = "";
        for (String languageItem : selectedLanguagesList) {
            languages += languageItem + ",";
        }

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
        values.put(REFUGEE_COLUMN_LANGUAGE, languages);


        // after adding all values we are passing
        // content values to our table.
        db.insert(REFUGEE_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public void updateNotes(int userId, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT notes FROM " + REFUGEE_TABLE_NAME + " WHERE id = " + userId;
        Cursor cursor = db.rawQuery(query, null);
        String existingNotes = "";
        if (cursor.moveToFirst()) {
            existingNotes = cursor.getString(0);
        }
        String newNotes = existingNotes + "\n" + notes;
        values.put("notes", newNotes);
        db.update(REFUGEE_TABLE_NAME, values, "id=?", new String[]{String.valueOf(userId)});
        db.close();
    }
    public void addVolunteerTable(String username, String password, String telephone, ArrayList<String> selectedLanguagesList) {
        String languages = "";
        for (String languageItem : selectedLanguagesList) {
            languages += languageItem + ",";
        }
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
        values.put(VOLUNTEER_COLUMN_LANGUAGE, languages);

        // after adding all values we are passing
        // content values to our table.
        db.insert(VOLUNTEER_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

   /* public void AddNotesTable(int refugeeId, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("refugee_id", refugeeId);
        contentValues.put("notes", notes);
        db.insert("notes", null, contentValues);
        db.close();
    } */

    // In order to update the table, we need to delete it and create a new upgraded one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + REFUGEE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOLUNTEER_TABLE_NAME);
      //  db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE_NAME);

        // calling the onCreate method that creates the database
        onCreate(db);
    }
    public Refugees getRefugees(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(REFUGEE_TABLE_NAME, new String[] {REFUGEE_COLUMN_ID, REFUGEE_COLUMN_USERNAME, REFUGEE_COLUMN_PASSWORD},
                REFUGEE_COLUMN_USERNAME + "=? AND " + REFUGEE_COLUMN_PASSWORD + "=?", new String[] { username, password },
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Refugees refugees = new Refugees(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            db.close();
            return refugees;
        } else {
            return null;
        }
    }

    public Volunteers getVolunteers(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(VOLUNTEER_TABLE_NAME, new String[] {VOLUNTEER_COLUMN_ID, VOLUNTEER_COLUMN_USERNAME, VOLUNTEER_COLUMN_PASSWORD, VOLUNTEER_COLUMN_NUMBER},
                VOLUNTEER_COLUMN_USERNAME + "=? AND " + VOLUNTEER_COLUMN_PASSWORD + "=?", new String[] { username, password },
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Volunteers volunteers = new Volunteers (cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            db.close();
            return volunteers;
        } else {
            return null;
        }
    }
    public List<String> getAllNotes(int userId) {
        List<String> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT notes FROM " + REFUGEE_TABLE_NAME + " WHERE id = " + userId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String notes = cursor.getString(0);
                notesList.add(notes);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notesList;
    }
}
