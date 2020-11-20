package com.amol.waterbill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amol.waterbill.model.GenericResponse;
import com.amol.waterbill.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {
    private static final String TAG = "RegisterUser";

    private static TextView tvActionBarTitle, etConnection, etName,
            etMobile, etEmail, etAddress;
    private static Button btnRegisterConnection;
    private static String strConnection, strName,
            strMobile, strEmail, strAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        tvActionBarTitle = (TextView) findViewById(R.id.tvActionBarTitle);
        etConnection = (TextView) findViewById(R.id.etConnection);
        etName = (TextView) findViewById(R.id.etName);
        etMobile = (TextView) findViewById(R.id.etMobile);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etAddress = (TextView) findViewById(R.id.etAddress);

        tvActionBarTitle.setText("Register Connection");

        btnRegisterConnection = (Button) findViewById(R.id.btnRegisterConnection);
        btnRegisterConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerConnection();
            }
        });
    }

    public void registerConnection() {
        Log.d(TAG,"registerConnection");
        strConnection = etConnection.getText().toString();
        strName = etName.getText().toString();
        strMobile = etMobile.getText().toString();
        strEmail = etEmail.getText().toString();
        strAddress = etAddress.getText().toString();
        Call<GenericResponse> call = RetrofitClient.getInstance().getApi().water_user_register(
                strConnection,strName, strMobile,strEmail,strAddress);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                GenericResponse genericResponse = response.body();
                if (genericResponse.getError().equals("200")) {
                    Toast.makeText(getApplicationContext(),"Connection added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                    startActivity(intent);
                } else if (genericResponse.getError().equals("409")) {
                    Toast.makeText(getApplicationContext(),"Already Exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
            }
        });
    }

}