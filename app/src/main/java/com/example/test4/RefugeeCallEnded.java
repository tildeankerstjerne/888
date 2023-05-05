package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RefugeeCallEnded extends AppCompatActivity {
    EditText editText_notes_call_ended;
    Button go_to_home_notes, save_notes_callended;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_call_ended);

        go_to_home_notes = findViewById(R.id.go_to_home_notes);
        save_notes_callended = findViewById(R.id.save_notes_callended);
        editText_notes_call_ended = findViewById(R.id.editText_notes_call_ended);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("userId", -1);

        DatabaseHandler dbHandler = new DatabaseHandler(RefugeeCallEnded.this);

        go_to_home_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRefugeeMainPage();
            }
        });

        save_notes_callended.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String notes = editText_notes_call_ended.getText().toString().trim();
                Toast.makeText(RefugeeCallEnded.this, "Id:" + userId, Toast.LENGTH_SHORT).show();
                if (!notes.isEmpty()) {
                    dbHandler.updateNotes(userId, notes);
                    Toast.makeText(RefugeeCallEnded.this, "Notes saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RefugeeCallEnded.this, "Please enter some notes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openRefugeeMainPage(){
        Intent intent = new Intent(RefugeeCallEnded.this, RefugeeMainPage.class);
        startActivity(intent);
    }
}
