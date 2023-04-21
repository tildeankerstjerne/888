package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupCriteria extends AppCompatActivity {

    private Button button_create_user_signupcriteria;
    private Button button_create_volunteer_signupcriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup_criteria);

        button_create_user_signupcriteria = (Button) findViewById(R.id.button_create_user_signupcriteria);
        button_create_user_signupcriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeMainPage();
            }
        });

        button_create_volunteer_signupcriteria = (Button) findViewById(R.id.button_create_volunteer_signupcriteria);
        button_create_volunteer_signupcriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openVolunteerMainPage();
            }
        });
    }
    public void openRefugeeMainPage(){
        Intent intent = new Intent(this, RefugeeMainPage.class);
        startActivity(intent);
    }
    public void openVolunteerMainPage(){
        Intent intent = new Intent(this, VolunteerMainPage.class);
        startActivity(intent);
    }
}