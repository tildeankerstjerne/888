package com.example.test4;

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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

public class RefugeeProfile extends AppCompatActivity {
    private Button button_edit_refugee_profile;
    private ImageView button_back_profile;
    private TextView text_speaklanguage_refugee, text_username_refugee, text_number_refugee;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_refugee_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        DatabaseHandler dbHandler = new DatabaseHandler(RefugeeProfile.this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        text_username_refugee = (TextView) findViewById(R.id.text_username_refugee);
        text_number_refugee = (TextView) findViewById(R.id.text_number_refugee);
        text_speaklanguage_refugee = (TextView) findViewById(R.id.text_speaklanguage_refugee);
        button_edit_refugee_profile = (Button) findViewById(R.id.button_edit_refugee_profile);

        // query the database to retrieve the refugee's information
        String refugeeQuery = "SELECT * FROM " + DatabaseHandler.REFUGEE_TABLE_NAME + " WHERE " + DatabaseHandler.REFUGEE_COLUMN_ID + " = " + userId;
        Cursor refugeeCursor = db.rawQuery(refugeeQuery, null);
        if (refugeeCursor.moveToFirst()) {
            String spokenLanguages = refugeeCursor.getString(refugeeCursor.getColumnIndex("language"));
            text_speaklanguage_refugee.setText(spokenLanguages);
            String usernameRefugee = refugeeCursor.getString(refugeeCursor.getColumnIndex("username"));
            text_username_refugee.setText(usernameRefugee);
            String numberRefugee = refugeeCursor.getString(refugeeCursor.getColumnIndex("number"));
            text_number_refugee.setText(numberRefugee);
        }
        refugeeCursor.close();
        db.close();

        button_edit_refugee_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRefugeeEditProfile();
            }
        });

        button_back_profile = (ImageView) findViewById(R.id.button_back_refugee);
        button_back_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRefugeeMenu();
            }

        });
    }
    public void openRefugeeEditProfile(){
        Intent intent = new Intent(this, RefugeeEditProfile.class);
        startActivity(intent);
    }
    public void openRefugeeMenu(){
        Intent intent = new Intent(this, RefugeeMenu.class);
        startActivity(intent);
    }


}