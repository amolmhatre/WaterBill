package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amol.waterbill.model.GenericResponse;
import com.amol.waterbill.network.RetrofitClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetail extends AppCompatActivity {

    private static final String TAG = "CustomerDetail";

    private static TextView tvActionBarTitle,tvName,tvConnection,tvAddress,tvLastReading;
    private static EditText etCurrentReading;
    private static SharedPreferences sharedPreferences;
    private static Button btnCreateBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.SHARED_PREFERENCE_KEY), MODE_PRIVATE);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        //getSupportActionBar().setElevation(0);


        /** Initialize UI components */
        tvName = (TextView) findViewById(R.id.tvName);
        tvConnection = (TextView) findViewById(R.id.tvConnection);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvLastReading = (TextView) findViewById(R.id.tvLastReading);
        tvActionBarTitle = (TextView) findViewById(R.id.tvActionBarTitle);
        etCurrentReading = (EditText) findViewById(R.id.etCurrentReading);
        btnCreateBill = (Button) findViewById(R.id.btnCreateBill);

        /** Setting values to UI components */
        tvName.setText(sharedPreferences.getString("name","User Name"));
        tvConnection.setText(sharedPreferences.getString("connectionNumber","Connection Number"));
        tvAddress.setText(sharedPreferences.getString("address","User Address"));

        String strDate = sharedPreferences.getString("bill_cycle",null);
        tvLastReading.setText(getMonth(Integer.parseInt(strDate.substring(5,7)))+" "+
                strDate.substring(0,4)+"\n"+
                sharedPreferences.getString("current_reading","Last Reading"));

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        tvActionBarTitle.setText(getMonth(month)+" "+year);

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateBill();
            }
        });
    }

    /** Called when the user taps button */
    public void generateBill() {
        Log.d(TAG,"generateBill");
        String currentReading = etCurrentReading.getText().toString();
        int intCurrentReading = Integer.parseInt(currentReading);
        int intLastReaading = Integer.parseInt(sharedPreferences.getString("current_reading",null));
        int intUnit = intCurrentReading-intLastReaading;
        int intUnitPrice = Integer.parseInt(sharedPreferences.getString("unit_price",null));
        int intAmount = intUnit*intUnitPrice;

        Call<GenericResponse> call = RetrofitClient.getInstance().getApi().water_create_bill(
                intUnit+"",
                intUnitPrice+"",
                intAmount+"",
                intCurrentReading+"",
                intLastReaading+"",
                sharedPreferences.getString("user_id",null),
                sharedPreferences.getString("connectionNumber",null),
                "created"
        );

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                billGenerated(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });
    }

    public void billGenerated(String bill_number){
        Intent intent = new Intent(this, BillGenerated.class);
        intent.putExtra("bill_number",bill_number);
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