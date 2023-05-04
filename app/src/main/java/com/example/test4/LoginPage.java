package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private TextView button_create_user_login;

    private Button button_continue_login, button_continue_volunteer_login, button_login;
    private EditText enter_username_login, enter_password_login;
    private DatabaseHandler dbHandler;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        dbHandler = new DatabaseHandler(LoginPage.this);

        button_create_user_login = findViewById(R.id.button_create_user_login);
        enter_username_login = findViewById(R.id.enter_username_login);
        enter_password_login = findViewById(R.id.enter_password_login);

        button_create_user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openSignupPage();
            }
        });

        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = enter_username_login.getText().toString();
                String password = enter_password_login.getText().toString();
                Refugees refugees = dbHandler.getRefugees(username, password);
                Volunteers volunteers = dbHandler.getVolunteers(username, password);
                if (refugees != null) {
                    // User exists, open the RefugeeMainPage
                    openRefugeeMainPage();
                } else if (volunteers != null) {
                    openVolunteerMain();
                }
                else {
                    Toast.makeText(LoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openRefugeeMainPage(){
        Intent intent = new Intent(LoginPage.this, RefugeeMainPage.class);
        startActivity(intent);
    }

    public void openSignupPage(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }

    public void openVolunteerMain(){
        Intent intent = new Intent(this, VolunteerMainPage.class);
        startActivity(intent);
    }
}
