package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class VolunteerEditProfile extends AppCompatActivity {
    private Button button_saveprofile_vol;
    private ImageView button_back_edit_vol;
    private EditText username_vol, password_vol, number_vol;
    private Spinner language_spinner_editVol;
    private String[] languagesArray;
    private DatabaseHandler dbHandler;
    private ArrayList<String> selectedLanguagesList;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_volunteer_edit_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);
        dbHandler = new DatabaseHandler(VolunteerEditProfile.this);

        username_vol = findViewById(R.id.editText_username_vol);
        password_vol = findViewById(R.id.editText_password_vol);
        number_vol = findViewById(R.id.editText_number_vol);

        language_spinner_editVol = findViewById(R.id.language_spinner_editVol);
        languagesArray = getResources().getStringArray(R.array.languages_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languagesArray);
        language_spinner_editVol.setAdapter(adapter);

        selectedLanguagesList = new ArrayList<>();
        language_spinner_editVol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        button_saveprofile_vol = findViewById(R.id.button_saveprofile_vol);
        button_saveprofile_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_vol.getText().toString();
                String password = password_vol.getText().toString();
                String telephone = number_vol.getText().toString();
                String languages = String.join(",", selectedLanguagesList);

                dbHandler.updateVolunteerTable(userId, username, password, telephone, languages);

                Toast.makeText(VolunteerEditProfile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                openVolunteerProfile();
            }
        });

        button_back_edit_vol = (ImageView) findViewById(R.id.button_back_edit_vol);
        button_back_edit_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVolunteerProfile();
            }
        });
    }
    public void openVolunteerProfile() {
        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }
}
