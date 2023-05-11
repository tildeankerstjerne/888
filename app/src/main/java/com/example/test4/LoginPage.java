package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private TextView button_create_user_login;

    private ImageView imageview_back_loginpage;

    private Button button_continue_login, button_continue_volunteer_login, button_login;
    private EditText enter_username_login, enter_password_login;
    private DatabaseHandler dbHandler;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar

        setContentView(R.layout.activity_login_page);

        dbHandler = new DatabaseHandler(LoginPage.this);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        button_create_user_login = findViewById(R.id.button_create_user_login);
        enter_username_login = findViewById(R.id.enter_username_login);
        enter_password_login = findViewById(R.id.enter_password_login);

        imageview_back_loginpage = (ImageView) findViewById(R.id.imageview_back_loginpage);
        imageview_back_loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openChooseSignupLogin();
            }
        });

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
                    saveUserId(Integer.parseInt(refugees.getID()));
                    // User exists, open the RefugeeMainPage
                    openRefugeeMainPage();
                } else if (volunteers != null) {
                    saveUserId(Integer.parseInt(volunteers.getID()));
                    openVolunteerMain();
                }
                else {
                    Toast.makeText(LoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }
    private int getUserId() {
        return sharedPreferences.getInt("userId", -1); // default value of -1 if not found
    }

    public void openRefugeeMainPage(){
        Intent intent = new Intent(LoginPage.this, RefugeeMainPage.class);
        startActivity(intent);
    }

    private void openChooseSignupLogin(){
        Intent intent = new Intent(LoginPage.this, ChooseSignupLogin.class);
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
