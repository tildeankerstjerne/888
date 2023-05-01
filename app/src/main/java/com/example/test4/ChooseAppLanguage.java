package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class ChooseAppLanguage extends AppCompatActivity {

    private ImageButton dansk_sprog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_app_language);

        dansk_sprog = (ImageButton) findViewById(R.id.button_flag_dk1);
        dansk_sprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                SignupPage();
            }
        });

    }

    public void SignupPage(){
        Intent intent = new Intent(this, ChooseSignupLogin.class);
        startActivity(intent);
    }

}