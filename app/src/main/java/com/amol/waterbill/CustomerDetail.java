package com.amol.waterbill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerDetail extends AppCompatActivity {

    TextView tvBillCycle,tvName,tvConnection,tvAddress,tvLastReading;
    EditText etCurrentReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("connection");

        tvBillCycle = (TextView) findViewById(R.id.tvBillCycle);
        tvName = (TextView) findViewById(R.id.tvName);
        tvConnection = (TextView) findViewById(R.id.tvConnection);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvLastReading = (TextView) findViewById(R.id.tvLastReading);
        etCurrentReading = (EditText) findViewById(R.id.etCurrentReading);

        // Capture the layout's TextView and set the string as its text
        tvConnection.setText(message);
    }

    /** Called when the user taps button */
    public void generateBill(View view) {
        Intent intent = new Intent(this, BillGenerated.class);
        String currentReading = etCurrentReading.getText().toString();
        intent.putExtra("currentReading", currentReading);
        startActivity(intent);
    }

}