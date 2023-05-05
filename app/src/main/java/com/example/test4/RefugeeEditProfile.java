package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeEditProfile extends AppCompatActivity {

    private Button button_back_edit_profile;
    private Button button_saveprofile_editprofile;

    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_edit_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        button_back_edit_profile = (Button) findViewById(R.id.button_back_edit_profile);
        button_back_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRefugeeProfile();
            }
        });

        button_saveprofile_editprofile = (Button) findViewById(R.id.button_saveprofile_editprofile);
        button_saveprofile_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openRefugeeProfile(); }
        });
    }
    public void openRefugeeProfile() {
        Intent intent = new Intent(this, RefugeeProfile.class);
        startActivity(intent);
    }
}
