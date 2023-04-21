package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeNotes extends AppCompatActivity {
    private Button button_edit_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_notes);

        button_edit_notes = (Button) findViewById(R.id.button_edit_notes);
        button_edit_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeEditNotes();
            }
        });
    }
    public void openRefugeeEditNotes(){
        Intent intent = new Intent(this, RefugeeEditNotes.class);
        startActivity(intent);
    }
}