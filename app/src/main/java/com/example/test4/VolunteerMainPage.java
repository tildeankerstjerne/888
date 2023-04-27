package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VolunteerMainPage extends AppCompatActivity {

    private ImageView volunteer_menu_main;

    private ImageView baseline_circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_main_page);

        volunteer_menu_main = (ImageView) findViewById(R.id.volunteer_menu_main);
        volunteer_menu_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openMenu();
            }
        });

        final ImageView volunteer_availability = findViewById(R.id.volunteer_availability);
        volunteer_availability.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("PrivateResource")
            @Override
            public void onClick(View v) {
                volunteer_availability.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_fab_shadow_mid_color));
            }
        });

    }
    public void openMenu(){
        Intent intent = new Intent(this, VolunteerMenu.class);
        startActivity(intent);
    }
}