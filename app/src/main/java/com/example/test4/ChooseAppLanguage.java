package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseAppLanguage extends AppCompatActivity {

    private TextView dansk_sprog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_app_language);

        dansk_sprog = (TextView) findViewById(R.id.Dansk);
        dansk_sprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                SignupPage();
            }
        });

    }

    public void SignupPage(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }

}