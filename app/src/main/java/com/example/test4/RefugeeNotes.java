package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.List;

public class RefugeeNotes extends AppCompatActivity {

    private Button button_edit_notes;
    private ImageView button_back_notes;
    private TextView text_note;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_refugee_notes);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        text_note = findViewById(R.id.text_note);

        DatabaseHandler dbHandler = new DatabaseHandler(RefugeeNotes.this);

        List<String> allNotes = dbHandler.getAllNotes(userId);
        String notesString = TextUtils.join("\n", allNotes);
        text_note.setText(notesString);

        button_back_notes = (ImageView) findViewById(R.id.button_back_notes);
        button_back_notes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRefugeeMenu();
            }
        });
    }

    public void openRefugeeMenu(){
        Intent intent = new Intent(this, RefugeeMenu.class);
        startActivity(intent);
    }
}