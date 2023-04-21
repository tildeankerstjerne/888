package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeMenu extends AppCompatActivity {
    private Button button_profile_menu;
    private Button button_notes_menu;
    private Button button_logoff_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_menu);

        button_profile_menu = (Button) findViewById(R.id.button_profile_menu);
        button_profile_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeProfile();
            }
        });
        button_notes_menu = (Button) findViewById(R.id.button_notes_menu);
        button_notes_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeNotes();
            }
        });
        button_logoff_menu = (Button) findViewById(R.id.button_logoff_menu);
        button_logoff_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openSignupLogin();
            }
        });
    }
    public void openRefugeeProfile(){
        Intent intent = new Intent(this, RefugeeProfile.class);
        startActivity(intent);
    }
    public void openRefugeeNotes(){
        Intent intent = new Intent(this, RefugeeNotes.class);
        startActivity(intent);
    }
    public void openSignupLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}