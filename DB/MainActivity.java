package com.example.practical_8;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText prnEditText, nameEditText, idEditText;
    TextView output;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prnEditText = findViewById(R.id.prnEditText);
        nameEditText = findViewById(R.id.nameEditText);
        idEditText = findViewById(R.id.idEditText);
        output = findViewById(R.id.output);

        databaseHelper = new DatabaseHelper(this);
        read();
    }

    public void insert(View view) {
        if(!prnEditText.getText().toString().trim().isEmpty() && !nameEditText.getText().toString().trim().isEmpty()) {
            int prn = Integer.parseInt(prnEditText.getText().toString());
            String name = nameEditText.getText().toString().trim();
            long returnVal = databaseHelper.insert(prn, name);
            if(returnVal != 0) {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
            read();
        }
    }

    public void delete(View view) {
        if(!idEditText.getText().toString().trim().isEmpty()) {
            int id = Integer.parseInt(idEditText.getText().toString().trim());
            long returnVal = databaseHelper.delete(id);
            if(returnVal != 0) {
                Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        read();
    }

    public void read() {
        Cursor cursor = databaseHelper.read();
        String data = "";
        while (cursor.moveToNext()) {
            data += cursor.getInt(0) + "\t | " + cursor.getInt(1) + "\t | " + cursor.getString(2) + "\n";
        }
        if(!data.trim().isEmpty()) {
            output.setText(data);
        } else {
            output.setText("No Records Found");
        }
    }
}