package com.example.test4;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText _username, _password, _telephone;

    private CheckBox _language;
    private Button ContinueButton;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        // initializing all our variables.
        _username = findViewById(R.id.editText_username_signuppage);
        _password = findViewById(R.id.editText_password_signuppage);
        _telephone = findViewById(R.id.editText_number_signuppage);
        _language = findViewById(R.id.checkBox_danish);


        ContinueButton = findViewById(R.id.button_continue_signup);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DatabaseHandler(SignupPage.this);

        // below line is to add on click listener for our add course button.
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String username = _username.getText().toString();
                String password = _password.getText().toString();
                String telephone = _telephone.getText().toString();
                String language = _language.getText().toString();

                /*
                String danish = _danish.getText().toString();
                String english = _english.getText().toString();
                String refugee = _refugee.getText().toString();
                */


                // validating if the text fields are empty or not.
                if (username.isEmpty()||password.isEmpty()||telephone.isEmpty()) {
                    Toast.makeText(SignupPage.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                else
                {
                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandler.addRefugeeTable(username, password, telephone, language);

    public void addStuffClick(View view) //denne bliver aldrig brugt??
    {
        Accounts accounts = new Accounts(editText_username_signuppage.getText().toString());
        dbHandler.addAccounts((accounts));
        printDatabase();
    }


                    // after adding the data we are displaying a toast message.
                    openSignupCriteria();
                }

            }
        });
    }

    public void openSignupCriteria(){
        Intent intent = new Intent(this, SignupCriteria.class);
        startActivity(intent);
    }
}