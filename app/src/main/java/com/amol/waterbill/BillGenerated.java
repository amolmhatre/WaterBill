package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillGenerated extends AppCompatActivity {
    private static final String TAG = "BillGenerated";
    private static SharedPreferences sharedPreferences;
    private static TextView tvBillNumber, tvName,tvConnection,
            tvAddress, tvCurrentReading, tvUnit,
            tvBillAmount, tvEmail, tvPhone;
    private static Button btnHome;
    private static String bill_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_generated);
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.SHARED_PREFERENCE_KEY), MODE_PRIVATE);

        tvBillNumber = (TextView) findViewById(R.id.tvBillNumber);
        tvName = (TextView) findViewById(R.id.tvName);
        tvConnection = (TextView) findViewById(R.id.tvConnection);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvCurrentReading = (TextView) findViewById(R.id.tvCurrentReading);
        tvUnit = (TextView) findViewById(R.id.tvUnit);
        tvBillAmount = (TextView) findViewById(R.id.tvBillAmount);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        btnHome = (Button) findViewById(R.id.btnHome);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        //getSupportActionBar().setElevation(0);

        Intent intent = getIntent();
        bill_number = intent.getStringExtra("bill_number");


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });
        getUserData();
    }

    private void getUserData() {
        Log.d(TAG,sharedPreferences.getString("name", "user name")+"\n"+
                sharedPreferences.getString("connectionNumber", "user name")+"\n"+
                sharedPreferences.getString("address", "user name")+"\n"+
                sharedPreferences.getString("current_reading", "user name")+"\n"+
                sharedPreferences.getString("unit", "user name")+"\n"+
                sharedPreferences.getString("amount", "user name")+"\n"+
                sharedPreferences.getString("email", "user name")+"\n"+
                sharedPreferences.getString("mobile_no", "user name")+"\n");

        tvBillNumber.setText(bill_number);
        tvName.setText("नाव: "+
                sharedPreferences.getString("name", "user name"));
        tvConnection.setText("कनेक्शन नंबर: "+
                sharedPreferences.getString("connectionNumber", "connection"));
        tvAddress.setText("पत्ता: "+
                sharedPreferences.getString("address", "address"));
        tvCurrentReading.setText("रिडींग: "+
                sharedPreferences.getString("current_reading", "current reading"));
        tvUnit.setText("युनिट: "+
                sharedPreferences.getString("unit", "unit"));
        tvBillAmount.setText("पाणी बिल: ₹"+
                sharedPreferences.getString("amount", "amount"));
        tvEmail.setText("Email sent to "+
                sharedPreferences.getString("email", "email"));
        tvPhone.setText("SMS sent to "+
                sharedPreferences.getString("mobile_no", "phone"));
    }

        // Called when the user taps button */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}