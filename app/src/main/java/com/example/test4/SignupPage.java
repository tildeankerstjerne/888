package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupPage extends AppCompatActivity {
    // laver klassen button

    private Button button_continue_signup;
    private EditText editText_username_signuppage, editText_password_signuppage;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);


        button_continue_signup = (Button) findViewById(R.id.button_continue_signup);
        editText_username_signuppage = (EditText) findViewById(R.id.editText_username_signuppage);
        editText_password_signuppage = (EditText) findViewById(R.id.editText_password_signuppage);


        button_continue_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupCriteria();

            }
        });
        dbHandler = new DatabaseHandler(this, null, null, 1);

    }

    public void printDatabase() {
        String dbstring = dbHandler.databaseToString();
        editText_username_signuppage.setText("");
        editText_password_signuppage.setText("");

    }

    public void addStuffClick(View view) //denne bliver aldrig brugt??
    {
        Accounts accounts = new Accounts(editText_username_signuppage.getText().toString());
        dbHandler.addAccounts((accounts));
        printDatabase();
    }



    public void openSignupCriteria(){
        Intent intent = new Intent(this, SignupCriteria.class);
        startActivity(intent);
    }
}