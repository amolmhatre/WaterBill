package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amol.waterbill.model.GenericResponse;
import com.amol.waterbill.network.RetrofitClient;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBill extends AppCompatActivity {

    private static final String TAG = "CreateBill";

    private static TextView tvActionBarTitle,tvName,tvConnection,
            tvAddress,tvLastReading,tvPendingBill;
    private static EditText etCurrentReading;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Button btnCreateBill;
    private static Spinner spinnerMonth, spinnerYear;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bill);
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.SHARED_PREFERENCE_KEY), MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
        tvPendingBill = (TextView) findViewById(R.id.tvPendingBill);
        etCurrentReading = (EditText) findViewById(R.id.etCurrentReading);
        btnCreateBill = (Button) findViewById(R.id.btnCreateBill);
        spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        spinnerYear = (Spinner) findViewById(R.id.spinnerYear);

        ArrayAdapter monthAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.month_english));
        ArrayAdapter yearAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.year));
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /** Setting the ArrayAdapter data on the Spinner */
        spinnerMonth.setAdapter(monthAdapter);
        spinnerYear.setAdapter(yearAdapter);

        /** Setting values to UI components */
        tvName.setText(sharedPreferences.getString("name","User Name"));
        tvConnection.setText(sharedPreferences.getString("connectionNumber","Connection Number"));
        tvAddress.setText(  sharedPreferences.getString("address","User Address"));
        tvLastReading.setText(sharedPreferences.getString("bill_cycle","Bill Cycle")+"\n"+
                sharedPreferences.getString("current_reading","Last Reading")+" युनिट");
        tvPendingBill.setText("₹ "+sharedPreferences.getString("pending_bill","pending_bill"));

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        tvActionBarTitle.setText(getMonth(month)+" "+year);

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentReading = etCurrentReading.getText().toString();
                if (Integer.parseInt(currentReading)>Integer.parseInt(sharedPreferences.getString("current_reading",null))) {
                    generateBill(currentReading);
                } else {
                    Toast.makeText(getBaseContext(),"चालू महिन्याची reading मागच्या reading पेक्षा जास्त असावी ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /** Called when the user taps button */
    public void generateBill(String currentReading) {
        Log.d(TAG,"generateBill");
        int intCurrentReading = Integer.parseInt(currentReading);
        int intLastReaading = Integer.parseInt(sharedPreferences.getString("current_reading",null));
        int intUnit = intCurrentReading-intLastReaading;
        int intUnitPrice = Integer.parseInt(sharedPreferences.getString("unit_price",null));
        int intAmount = intUnit*intUnitPrice;

        Log.d(TAG,"\nCurrent Reading "+intCurrentReading+
                "\nLastReading: "+intLastReaading+
                "\nTotal=>"+intUnit+" * "+intUnitPrice+" = "+intAmount);

        String bill_cycle = spinnerMonth.getSelectedItem().toString()+"-"+
                spinnerYear.getSelectedItem().toString();

        editor.putString("amount", intAmount+"");
        editor.putString("unit", intUnit+"");
        editor.putString("current_reading",intCurrentReading+"");
        editor.putString("bill_cycle", bill_cycle);
        editor.apply();

        Call<GenericResponse> call = RetrofitClient.getInstance().getApi().water_create_bill(
                intUnit+"",
                intUnitPrice+"",
                intAmount+"",
                intCurrentReading+"",
                intLastReaading+"",
                bill_cycle,
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