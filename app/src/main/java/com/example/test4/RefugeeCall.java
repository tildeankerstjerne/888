package com.example.test4;

import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_ID;
import static com.example.test4.DatabaseHandler.REFUGEE_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.REFUGEE_TABLE_NAME;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_LANGUAGE;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_NUMBER;
import static com.example.test4.DatabaseHandler.VOLUNTEER_COLUMN_STATUS;
import static com.example.test4.DatabaseHandler.VOLUNTEER_TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class RefugeeCall extends AppCompatActivity {
    EditText phoneNo;

    private ImageView imageview_back_call;
    FloatingActionButton callbtn;
    static int PERMISSION_CODE = 100;
    static int CALL_CODE = 101;
    private TextView textview_volunteer_number;
    private Button show_number_refugeemain;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  // this line hides the actionbar
        setContentView(R.layout.activity_refugee_call);
        phoneNo = findViewById(R.id.editTextPhone);
        callbtn = findViewById(R.id.callbtn);
        textview_volunteer_number = findViewById(R.id. textview_volunteer_number);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        show_number_refugeemain = (Button) findViewById(R.id.show_number_refugeemain);

        show_number_refugeemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(RefugeeCall.this);
                SQLiteDatabase db = dbHandler.getReadableDatabase();
                String[] selectionArgs = {String.valueOf(userId)};
                String refugeeQuery = "SELECT " + REFUGEE_COLUMN_LANGUAGE + " FROM " + REFUGEE_TABLE_NAME + " WHERE " + REFUGEE_COLUMN_ID + " = ?";
                Cursor cursor = db.rawQuery(refugeeQuery, selectionArgs);
                if (cursor.moveToFirst()) {
                    String refugeeLanguage = cursor.getString(cursor.getColumnIndex(REFUGEE_COLUMN_LANGUAGE));
                    String[] refugeeLanguages = refugeeLanguage.split(",");
                    String volunteerQuery = "SELECT * FROM " + VOLUNTEER_TABLE_NAME + " WHERE ";
                    for (int i = 0; i < refugeeLanguages.length; i++) {
                        if (i > 0) {
                            volunteerQuery += " OR ";
                        }
                        volunteerQuery += VOLUNTEER_COLUMN_LANGUAGE + " LIKE '%" + refugeeLanguages[i] + "%'";
                    }
                    volunteerQuery += " AND " + VOLUNTEER_COLUMN_STATUS + " = 'active' ORDER BY RANDOM() LIMIT 1";
                    Cursor volunteerCursor = db.rawQuery(volunteerQuery, null);

                    if (volunteerCursor.moveToFirst()) {
                        String volunteerNumber = volunteerCursor.getString(volunteerCursor.getColumnIndex(VOLUNTEER_COLUMN_NUMBER));
                        TextView volunteerNumberTextView = findViewById(R.id.textview_volunteer_number);
                        volunteerNumberTextView.setText("Volunteer's number: " + volunteerNumber);
                    } else {
                        Toast.makeText(RefugeeCall.this, "Failed to find a volunteer with the same language as you, try again later", Toast.LENGTH_LONG).show();
                    }
                    volunteerCursor.close();
                } else {
                    Toast.makeText(RefugeeCall.this, "Failed to find your language, try again later", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        });



        if (ContextCompat.checkSelfPermission(RefugeeCall.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(RefugeeCall.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
        }

        imageview_back_call = (ImageView) findViewById(R.id.imageview_back_call);
        imageview_back_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalder på metoden vi laver nedenunder
                openRefugeeMainPage();
            }
        });
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = phoneNo.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + phoneno));
                startActivityForResult(i, CALL_CODE);
            }
        });
    }

    public void openRefugeeMainPage(){
        Intent intent = new Intent(RefugeeCall.this, RefugeeMainPage.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_CODE) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("userId", userId);
            editor.apply();

            Intent intent = new Intent(this, RefugeeCallEnded.class);
            startActivity(intent);
        }
    }
}
