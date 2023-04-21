package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefugeeEditNotes extends AppCompatActivity {
    private Button button_back_edit_notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugee_edit_notes);

        button_back_edit_notes = (Button) findViewById(R.id.button_back_edit_notes);
        button_back_edit_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeNotes();
            }
        });
    }

    public void openRefugeeNotes(){
        Intent intent = new Intent(this, RefugeeNotes.class);
        startActivity(intent);
    }
}