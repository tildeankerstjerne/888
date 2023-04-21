package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VolunteerMainPage extends AppCompatActivity {
    private Button button_menu_volunteer_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_main_page);

        button_menu_volunteer_main = (Button) findViewById(R.id.button_menu_volunteer_main);
        button_menu_volunteer_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openMenu();
            }
        });
    }
    public void openMenu(){
        Intent intent = new Intent(this, VolunteerMenu.class);
        startActivity(intent);
    }
}