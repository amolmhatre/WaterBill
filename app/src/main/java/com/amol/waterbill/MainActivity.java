package com.amol.waterbill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText etConnectionNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etConnectionNumber =  (EditText) findViewById(R.id.etConnectionNumber);
    }

    /** Called when the user taps button */
    public void searchConnection(View view) {
        Intent intent = new Intent(this, CustomerDetail.class);
        String connectionNumber = etConnectionNumber.getText().toString();
        intent.putExtra("connection", connectionNumber);
        startActivity(intent);
    }
}