package com.example.test4;

import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_ID;
import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.REFUGEE_TABLE_NAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_USERNAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeMainPage extends AppCompatActivity {

    private Button button_emergency_refugee_main;
    private Button button_menu_refugee_main;

    private Button button_start_call_refugee_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_main_page);

        button_start_call_refugee_main = (Button) findViewById(R.id.button_start_call_refugee_main);

        button_start_call_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                SQLiteDatabase db = getReadableDatabase();
                String[] selectionArgs = {String.valueOf(refugeeId)};
                Cursor cursor = db.rawQuery("SELECT * FROM " + VOLUNTEER_TABLE_NAME + " WHERE " + VOLUNTEER_COLUMN_LANGUAGE + " = (SELECT " + REFUGEE_COLUMN_LANGUAGE + " FROM " + REFUGEE_TABLE_NAME + " WHERE " + REFUGEE_COLUMN_ID + " = ?) ORDER BY RANDOM() LIMIT 1", selectionArgs);
                if (cursor.moveToFirst()) {
                    String volunteerName = cursor.getString(cursor.getColumnIndex(VOLUNTEER_COLUMN_USERNAME));
                    // Do something with the volunteer's name
                }
                cursor.close();

                 */

                openRefugeeCall();
            }
        });



        button_emergency_refugee_main = (Button) findViewById(R.id.button_emergency_refugee_main);
        button_emergency_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openEmergencyInfoPage();
            }
        });
        button_menu_refugee_main = (Button) findViewById(R.id.button_menu_refugee_main);
        button_menu_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openMenu();
            }
        });
    }
    public void openEmergencyInfoPage(){
        Intent intent = new Intent(this, EmergencyInfo.class);
        startActivity(intent);
    }
    public void openMenu(){
        Intent intent = new Intent(this, RefugeeMenu.class);
        startActivity(intent);
    }

    public void openRefugeeCall(){
        Intent intent = new Intent(this, RefugeeCall.class);
        startActivity(intent);
    }
}