package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class RefugeeEditProfile extends AppCompatActivity {

    private Button button_saveprofile_editprofile;
    private ImageView button_back_edit_profile;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_refugee_edit_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        button_back_edit_profile = (ImageView) findViewById(R.id.button_back_edit_profile);
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
