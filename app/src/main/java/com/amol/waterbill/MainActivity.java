package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static EditText etConnectionNumber;
    private static Button btnGetConnection,btnRegisterConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        //getSupportActionBar().setElevation(0);

        etConnectionNumber =  (EditText) findViewById(R.id.etConnectionNumber);
        btnGetConnection = (Button) findViewById(R.id.btnGetConnection);
        btnRegisterConnection = (Button) findViewById(R.id.btnRegisterConnection);

        btnGetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchConnection();
            }
        });

        btnRegisterConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerConnection();
            }
        });
    }

    /** Called when the user taps button */
    public void searchConnection() {
        Intent intent = new Intent(this, CustomerDetail.class);
        String connectionNumber = etConnectionNumber.getText().toString();
        intent.putExtra("connection", connectionNumber);
        startActivity(intent);
    }

    public void registerConnection() {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }
}