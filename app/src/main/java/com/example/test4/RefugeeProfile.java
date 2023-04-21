package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeProfile extends AppCompatActivity {
    private Button button_edit_refugee_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_profile);

        button_edit_refugee_profile = (Button) findViewById(R.id.button_edit_refugee_profile);
        button_edit_refugee_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeEditProfile();
            }
        });
    }
    public void openRefugeeEditProfile(){
        Intent intent = new Intent(this, RefugeeEditProfile.class);
        startActivity(intent);
    }
}