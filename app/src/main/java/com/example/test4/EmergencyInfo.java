package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyInfo extends AppCompatActivity {
    private Button button_close_emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_info);

        button_close_emergency = (Button) findViewById(R.id.button_close_emergency);
        button_close_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                backToMain();
            }
        });
    }
    public void backToMain(){
        Intent intent = new Intent(this, RefugeeMainPage.class);
        startActivity(intent);
    }
}