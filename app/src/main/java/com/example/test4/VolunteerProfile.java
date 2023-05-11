package com.example.test4;

import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_NUMBER;
import static com.example.test4.DatabaseHandler.VOLUNTEER_TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;


public class VolunteerProfile extends AppCompatActivity {
    private Button button_edit_volunteer_profile;

    private ImageView button_back_profile;
    private TextView text_speaklanguage_volunteer, text_username_volunteer, text_number_volunteer;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_volunteer_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        DatabaseHandler dbHandler = new DatabaseHandler(VolunteerProfile.this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        text_username_volunteer = (TextView) findViewById(R.id.text_username_volunteer);
        text_number_volunteer = (TextView) findViewById(R.id.text_number_volunteer);
        text_speaklanguage_volunteer = (TextView) findViewById(R.id.text_speaklanguage_volunteer);

        // query the database to retrieve the volunteer's information
        String volunteerQuery = "SELECT * FROM " + DatabaseHandler.VOLUNTEER_TABLE_NAME + " WHERE " + DatabaseHandler.VOLUNTEER_COLUMN_ID + " = " + userId;
        Cursor volunteerCursor = db.rawQuery(volunteerQuery, null);
        if (volunteerCursor.moveToFirst()) {
            String spokenLanguages = volunteerCursor.getString(volunteerCursor.getColumnIndex("language"));
            text_speaklanguage_volunteer.setText("Your languages: " + spokenLanguages);
            String usernameVolunteer = volunteerCursor.getString(volunteerCursor.getColumnIndex("username"));
            text_username_volunteer.setText("Your username: " + usernameVolunteer);
            String numberVolunteer = volunteerCursor.getString(volunteerCursor.getColumnIndex("number"));
            text_number_volunteer.setText("Your number: " + numberVolunteer);
        }
        volunteerCursor.close();
        db.close();


        button_edit_volunteer_profile = (Button) findViewById(R.id.button_edit_volunteer_profile);
        button_edit_volunteer_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openVolunteerEditProfile();
            }
        });

        button_back_profile = (ImageView) findViewById(R.id.button_back_profile);
        button_back_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openVolunteerMenu();
            }

        });
    }

    public void openVolunteerMenu(){
        Intent intent = new Intent(this, VolunteerMenu.class);
        startActivity(intent);
    }
}