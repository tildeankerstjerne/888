package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class RefugeeProfile extends AppCompatActivity {
    private Button button_edit_refugee_profile;
    private Button button_back_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_profile);

        button_edit_refugee_profile = (Button) findViewById(R.id.button_edit_refugee_profile);
        button_edit_refugee_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRefugeeEditProfile();
            }
        });

        button_back_profile = (Button) findViewById(R.id.button_back_profile);
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