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

public class VolunteerMenu extends AppCompatActivity {
    private Button volunteer_profile;
    private Button volunteer_logoff;
    private int userId;

    private ImageView button_menu_refugee_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_volunteer_menu);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        button_menu_refugee_main = (ImageView) findViewById(R.id.button_menu_refugee_main);
        button_menu_refugee_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openVolunteerMainPage();
            }
        });

        volunteer_profile = (Button) findViewById(R.id.volunteer_profile);
        volunteer_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openProfile();
            }
        });
        volunteer_logoff = (Button) findViewById(R.id.volunteer_logoff);
        volunteer_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openLogoff();
            }
        });
    }
    public void openProfile(){
        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }

    public void openVolunteerMainPage(){
        Intent intent = new Intent(this, VolunteerMainPage.class);
        startActivity(intent);
    }


    public void openLogoff(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}