package com.example.test4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

import android.content.ContentValues;

public class SignupPage extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler
    private EditText _username, _password, _telephone;

    private ImageView imageview_back_signuppage;
    private Spinner languageSpinner;
    private String[] languagesArray;
    private Button ContinueButton;

    private CheckBox checkBox_volunteer;

    private CheckBox checkBox_refugee;
    private RadioGroup radioGroup;
    private RadioButton radioButtonRefugee, radioButtonVolunteer;

    private DatabaseHandler dbHandler;
    private ArrayList<String> selectedLanguagesList;
    private SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_signup_page);


        // initializing all our variables.
        _username = findViewById(R.id.editText_username_signuppage);
        _password = findViewById(R.id.editText_password_signuppage);
        _telephone = findViewById(R.id.editText_number_signuppage);
        ContinueButton = findViewById(R.id.button_continue_signup);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonRefugee = findViewById(R.id.radioButtonRefugee);
        radioButtonVolunteer = findViewById(R.id.radioButtonVolunteer);


        // creating a new dbhandler object
        // and passing our context to it.
        dbHandler = new DatabaseHandler(SignupPage.this);

        languageSpinner = findViewById(R.id.language_spinner);
        languagesArray = getResources().getStringArray(R.array.languages_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languagesArray);
        languageSpinner.setAdapter(adapter);

        selectedLanguagesList = new ArrayList<>();
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();
                if (selectedLanguage.equals(languagesArray[0])) { // check if selected item is the default value
                    selectedLanguagesList.clear(); // clear the list if the default value is selected
                } else if (!selectedLanguagesList.contains(selectedLanguage)) {
                    selectedLanguagesList.add(selectedLanguage);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        imageview_back_signuppage = (ImageView) findViewById(R.id.imageview_back_signuppage);
        imageview_back_signuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openChooseSignupLogin();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(SignupPage.this, "Selected: " + checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonRefugee.isChecked()) {
                    // below line is to get data from all edit text fields.
                    String username = _username.getText().toString();
                    String password = _password.getText().toString();
                    String telephone = _telephone.getText().toString();
                    Refugees refugees = dbHandler.getRefugees(username, password);

                    // validating if the text fields are empty or not.
                    if (username.isEmpty() || password.isEmpty() || telephone.isEmpty()) {
                        Toast.makeText(SignupPage.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // on below line we are calling a method to add new
                        // course to sqlite data and pass all our values to it.
                        String languages = String.join(",", selectedLanguagesList);
                        dbHandler.addRefugeeTable(username, password, telephone, selectedLanguagesList);
                        //saveUserId(Integer.parseInt(refugees.getID()));
                        openLogin();

                    }

                } else if (radioButtonVolunteer.isChecked()) {
                    // below line is to get data from all edit text fields.
                    String username = _username.getText().toString();
                    String password = _password.getText().toString();
                    String telephone = _telephone.getText().toString();
                    Volunteers volunteers = dbHandler.getVolunteers(username, password);

                    // validating if the text fields are empty or not.
                    if (username.isEmpty() || password.isEmpty() || telephone.isEmpty()) {
                        Toast.makeText(SignupPage.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        String languages = String.join(",", selectedLanguagesList);
                        dbHandler.addVolunteerTable(username, password, telephone, selectedLanguagesList);
                       // saveUserId(Integer.parseInt(volunteers.getID()));
                        openLogin();
                    }
                }

            }

        });
    }

    private void openLogin(){
        Intent intent = new Intent(SignupPage.this, LoginPage.class);
        startActivity(intent);
    }

    private void openChooseSignupLogin(){
        Intent intent = new Intent(SignupPage.this, ChooseSignupLogin.class);
        startActivity(intent);
    }

   /* private void openRefugeeMainPage(){
       // int userId = getUserId();
        Intent intent = new Intent(SignupPage.this, RefugeeMainPage.class);
       // intent.putExtra("userId", userId);
        startActivity(intent);
    }*/
    /*private void openVolunteerMainPage(){
       // int userId = getUserId();
        Intent intent = new Intent(SignupPage.this, VolunteerMainPage.class);
       // intent.putExtra("userId", userId);
        startActivity(intent);
    }*/


    protected void onDestroy() {
        super.onDestroy();
        dbHandler.close();
    }
}