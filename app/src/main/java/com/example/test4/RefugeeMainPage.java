package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeMainPage extends AppCompatActivity {

    private Button button_emergency_refugee_main;
    private Button button_menu_refugee_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_main_page);

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
    }
    public void openEmergencyInfoPage(){
        Intent intent = new Intent(this, EmergencyInfo.class);
        startActivity(intent);
    }
    public void openMenu(){
        Intent intent = new Intent(this, RefugeeMenu.class);
        startActivity(intent);
    }
}