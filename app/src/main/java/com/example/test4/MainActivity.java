package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_main);

        // link the variables to the flag buttons via id
        ImageButton en = findViewById(R.id.button_flag_uk);
        ImageButton dk = findViewById(R.id.button_flag_dk);
        ImageButton arab = findViewById(R.id.button_flag_syria);
        ImageButton ukraine = findViewById(R.id.button_flag_ukraine);
        LanguageManager lang = new LanguageManager(this);

        // when the UK flag is pressed
        en.setOnClickListener(view ->
        {
            // implementing method (from the LanguageManager)
            lang.updateResource("en");
            Intent intent = new Intent(MainActivity.this, ChooseSignupLogin.class);
            startActivity(intent);
        });

        dk.setOnClickListener(view ->
        {
            lang.updateResource("da");
            Intent intentMain = new Intent(MainActivity.this, ChooseSignupLogin.class);
            startActivity(intentMain);
        });

        ukraine.setOnClickListener(view ->
        {
            lang.updateResource("uk");
            Intent intent = new Intent(MainActivity.this, ChooseSignupLogin.class);
            startActivity(intent);
        });

        arab.setOnClickListener(view ->
        {
            lang.updateResource("ar");
            Intent intent = new Intent(MainActivity.this, ChooseSignupLogin.class);
            startActivity(intent);
        });

    }
}