package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SignupCriteria extends AppCompatActivity {


    String[] item ={"English", "Ukrainian", "Arabic", "Danish"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItem;
    private Button button_create_user_signupcriteria;
    private Button button_create_volunteer_signupcriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_criteria);


        autoCompleteTextView = findViewById(R.id.auto_Complete);

        adapterItem = new ArrayAdapter<String>(this, R.layout.list_item,item);

        autoCompleteTextView.setAdapter(adapterItem);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(SignupCriteria.this, "item " + item, Toast.LENGTH_SHORT).show();
            }
        });


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