package com.example.test4;

import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_ID;
import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.REFUGEE_TABLE_NAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_NUMBER;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_USERNAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RefugeeMainPage extends AppCompatActivity {

    private Button button_emergency_refugee_main, button_menu_refugee_main, button_start_call_refugee_main, show_number_refugeemain;
    private TextView volunteerNumberTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_main_page);

        volunteerNumberTextView = findViewById(R.id.textview_volunteer_number);

        Intent intent = getIntent();
        int refugeeId = intent.getIntExtra("refugeeId", 0);

        show_number_refugeemain = (Button) findViewById(R.id.show_number_refugeemain);

        show_number_refugeemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(RefugeeMainPage.this);
                SQLiteDatabase db = dbHandler.getReadableDatabase();
                String[] selectionArgs = {String.valueOf(refugeeId)};
                Cursor cursor = db.rawQuery("SELECT * FROM " + VOLUNTEER_TABLE_NAME + " WHERE " + VOLUNTEER_COLUMN_LANGUAGE + " = (SELECT " + REFUGEE_COLUMN_LANGUAGE + " FROM " + REFUGEE_TABLE_NAME + " WHERE " + REFUGEE_COLUMN_ID + " = ?) ORDER BY RANDOM() LIMIT 1", selectionArgs);
                if (cursor.moveToFirst()) {
                    String volunteerNumber = cursor.getString(cursor.getColumnIndex(VOLUNTEER_COLUMN_NUMBER));
                    TextView volunteerNumberTextView = findViewById(R.id.textview_volunteer_number);
                   volunteerNumberTextView.setText("Volunteer's number: " + volunteerNumber);
                   // Toast.makeText(RefugeeMainPage.this, "Volunteer's number:" + volunteerNumber, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RefugeeMainPage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
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
        button_start_call_refugee_main = (Button) findViewById(R.id.button_start_call_refugee_main);
        button_start_call_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRefugeeCall();
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