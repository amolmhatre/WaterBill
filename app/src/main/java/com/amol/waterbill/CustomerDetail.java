package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CustomerDetail extends AppCompatActivity {

    private static final String TAG = "CustomerDetail";

    private static TextView tvActionBarTitle,tvName,tvConnection,tvAddress,tvLastReading;
    private static EditText etCurrentReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        //getSupportActionBar().setElevation(0);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("connection");

        tvName = (TextView) findViewById(R.id.tvName);
        tvConnection = (TextView) findViewById(R.id.tvConnection);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvLastReading = (TextView) findViewById(R.id.tvLastReading);
        tvActionBarTitle = (TextView) findViewById(R.id.tvActionBarTitle);
        etCurrentReading = (EditText) findViewById(R.id.etCurrentReading);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        Log.d(TAG, "\nMonth: "+month+"\nYear:"+year);

        tvActionBarTitle.setText(getMonth(month)+" "+year);
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
    public static String getMonth(int month){
        String strMonth="January";
        switch (month) {
            case 0:
                strMonth = "January";
                break;
            case 1:
                strMonth = "February";
                break;
            case 2:
                strMonth = "March";
                break;
            case 3:
                strMonth = "April";
                break;
            case 4:
                strMonth = "May";
                break;
            case 5:
                strMonth = "June";
                break;
            case 6:
                strMonth = "July";
                break;
            case 7:
                strMonth = "August";
                break;
            case 8:
                strMonth = "September";
                break;
            case 9:
                strMonth = "October";
                break;
            case 10:
                strMonth = "November";
                break;
            case 11:
                strMonth = "December";
                break;
        }
        return strMonth;
    }

}