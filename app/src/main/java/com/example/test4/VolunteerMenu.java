package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VolunteerMenu extends AppCompatActivity {
    private Button volunteer_profile;
    private Button volunteer_logoff;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_menu);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

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
    public void openLogoff(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}