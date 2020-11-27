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

import com.amol.waterbill.model.UserModel;
import com.amol.waterbill.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static EditText etConnectionNumber;
    private static Button btnGetConnection,btnRegisterConnection;
    private static UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.SHARED_PREFERENCE_KEY), MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
                getConnection();
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
    public void getConnection() {
            Log.d(TAG,"getConnection");
             final String connectionNumber = etConnectionNumber.getText().toString();

            Call<UserModel> call = RetrofitClient.getInstance().getApi().get_Last_Bill(connectionNumber);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    userModel=response.body();
                    saveUserData(connectionNumber);
                }
                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                }
            });

    }

    private void saveUserData(String connectionNumber) {
        editor.putString("connectionNumber", connectionNumber);
        editor.putString("bill_no", userModel.getBillNo());
        editor.putString("bill_cycle", userModel.getBillCycle());
        editor.putString("unit", userModel.getUnit());
        editor.putString("unit_price", userModel.getUnitPrice());
        editor.putString("amount", userModel.getAmount());
        editor.putString("current_reading", userModel.getCurrentReading());
        editor.putString("user_id", userModel.getUserId());
        editor.putString("status", userModel.getStatus());
        editor.putString("name", userModel.getName());
        editor.putString("mobile_no", userModel.getMobileNo());
        editor.putString("email", userModel.getEmail());
        editor.putString("address", userModel.getAddress());
        editor.commit();
        getConnectionReading();
    }

    public void getConnectionReading() {
        Intent intent = new Intent(this, CustomerDetail.class);
        startActivity(intent);
    }

    public void registerConnection() {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }
}