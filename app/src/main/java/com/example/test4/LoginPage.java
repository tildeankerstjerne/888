package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button button_create_user_login;

    private Button button_continue_login;
    private Button button_continue_volunteer_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        button_create_user_login = (Button) findViewById(R.id.button_create_user_login);
        button_create_user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openSignupPage();
            }
        });
        button_continue_login = (Button) findViewById(R.id.button_continue_login);
        button_continue_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeMainPage();
            }
        });

        button_continue_volunteer_login = (Button) findViewById(R.id.button_continue_volunteer_login);
        button_continue_volunteer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openVolunteerMain();
            }
        });
    }
    public void openSignupPage(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
    public void openRefugeeMainPage(){
        Intent intent = new Intent(this, RefugeeMainPage.class);
        startActivity(intent);
    }
    public void openVolunteerMain(){
        Intent intent = new Intent(this, VolunteerMainPage.class);
        startActivity(intent);
    }
}