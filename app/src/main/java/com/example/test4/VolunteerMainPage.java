package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VolunteerMainPage extends AppCompatActivity {

    private ImageView volunteer_menu_main;

    private ImageView baseline_circle;
    private int userId;
    private RadioGroup radioGroupAvailability;
    private RadioButton radioButtonActive, radioButtonInactive;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_volunteer_main_page);

        dbHandler = new DatabaseHandler(VolunteerMainPage.this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        volunteer_menu_main = (ImageView) findViewById(R.id.volunteer_menu_main);
        volunteer_menu_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openMenu();
            }
        });

        radioGroupAvailability = findViewById(R.id.radioGroupAvailability);
        radioButtonActive = findViewById(R.id.radioButtonActive);
        radioButtonInactive = findViewById(R.id.radioButtonInactive);

        radioGroupAvailability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Toast.makeText(VolunteerMainPage.this, "Selected: " + checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                if (radioButtonActive.isChecked()) {
                    String status = "active";
                    dbHandler.addStatus(userId, status);
                }
                else if(radioButtonInactive.isChecked()){
                    String status = "inactive";
                    dbHandler.addStatus(userId, status);
                }
            }
        });

    }

    public void openMenu() {
        Intent intent = new Intent(this, VolunteerMenu.class);
        startActivity(intent);
    }
}
