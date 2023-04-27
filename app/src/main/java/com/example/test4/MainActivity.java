package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_login;
    private Button button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialiserer button med dens id som vi satte til button over i xml
        button_login = (Button) findViewById(R.id.button_open_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openLoginPage();
            }
        });


        button_signup = (Button) findViewById(R.id.button_open_signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openSignupPage();
            }
        });

    }

    public void openLoginPage(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
    public void openSignupPage(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
}