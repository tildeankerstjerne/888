package com.example.test4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_NOTES = "notes";
    private static final String COL_DANISH = "danish";
    private static final String COL_ENGLISH = "english";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating an sql query that creates the database, table and columns
        String query = "CREATE TABLE " + TABLE_ACCOUNTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_USERNAME + COL_PASSWORD + COL_NOTES + COL_DANISH + COL_ENGLISH;

        db.execSQL(query);

    }

    public void addAccounts (Accounts accounts) {

        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, accounts.get_username());
        values.put(COL_PASSWORD, accounts.get_password());
        values.put(COL_NOTES, accounts.get_notes());
        values.put(COL_DANISH, accounts.get_danish());
        values.put(COL_ENGLISH, accounts.get_english());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // this method is called to check if the table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);

        onCreate(db);

    }

    public String databaseToString(){

        String dbString="";

        SQLiteDatabase db=getWritableDatabase();

        String query= "SELECT * FROM " + TABLE_ACCOUNTS;
        Cursor c = db.rawQuery(query,null);
        return dbString;
    }
}
