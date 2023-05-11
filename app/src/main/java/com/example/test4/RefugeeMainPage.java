package com.example.test4;

import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_ID;
import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.REFUGEE_TABLE_NAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_NUMBER;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_STATUS;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_USERNAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;


public class RefugeeMainPage extends AppCompatActivity {

    private Button button_emergency_refugee_main, button_start_call_refugee_main, show_number_refugeemain;

    private ImageView button_menu_refugee_main;
    private TextView volunteerNumberTextView;
    private SharedPreferences sharedPreferences;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_refugee_main_page);
        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        volunteerNumberTextView = findViewById(R.id.textview_volunteer_number);


        if (userId != -1) {
            Toast.makeText(RefugeeMainPage.this, "userId is known" + userId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RefugeeMainPage.this, "Failed", Toast.LENGTH_SHORT).show();
        }

       /* show_number_refugeemain = (Button) findViewById(R.id.show_number_refugeemain);

        show_number_refugeemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(RefugeeMainPage.this);
                SQLiteDatabase db = dbHandler.getReadableDatabase();
                String[] selectionArgs = {String.valueOf(userId)};
                String refugeeQuery = "SELECT " + REFUGEE_COLUMN_LANGUAGE + " FROM " + REFUGEE_TABLE_NAME + " WHERE " + REFUGEE_COLUMN_ID + " = ?";
                Cursor cursor = db.rawQuery(refugeeQuery, selectionArgs);
                if (cursor.moveToFirst()) {
                    String refugeeLanguage = cursor.getString(cursor.getColumnIndex(REFUGEE_COLUMN_LANGUAGE));
                    String[] refugeeLanguages = refugeeLanguage.split(",");
                    String volunteerQuery = "SELECT * FROM " + VOLUNTEER_TABLE_NAME + " WHERE ";
                    for (int i = 0; i < refugeeLanguages.length; i++) {
                        if (i > 0) {
                            volunteerQuery += " OR ";
                        }
                        volunteerQuery += VOLUNTEER_COLUMN_LANGUAGE + " LIKE '%" + refugeeLanguages[i] + "%'";
                    }
                    volunteerQuery += " AND " + VOLUNTEER_COLUMN_STATUS + " = 'active' ORDER BY RANDOM() LIMIT 1";
                    Cursor volunteerCursor = db.rawQuery(volunteerQuery, null);

                    if (volunteerCursor.moveToFirst()) {
                        String volunteerNumber = volunteerCursor.getString(volunteerCursor.getColumnIndex(VOLUNTEER_COLUMN_NUMBER));
                        TextView volunteerNumberTextView = findViewById(R.id.textview_volunteer_number);
                        volunteerNumberTextView.setText("Volunteer's number: " + volunteerNumber);
                    } else {
                        Toast.makeText(RefugeeMainPage.this, "Failed to find a volunteer with the same language as you, try again later", Toast.LENGTH_LONG).show();
                    }
                    volunteerCursor.close();
                } else {
                    Toast.makeText(RefugeeMainPage.this, "Failed to find your language, try again later", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        }); */


        button_emergency_refugee_main = (Button) findViewById(R.id.button_emergency_refugee_main);
        button_emergency_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergencyInfoPage();
            }
        });
        button_menu_refugee_main = (ImageView) findViewById(R.id.button_menu_refugee_main);

        button_menu_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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