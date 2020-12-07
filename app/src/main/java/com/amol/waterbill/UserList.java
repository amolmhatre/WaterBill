package com.amol.waterbill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amol.waterbill.adapter.UserListAdapter;
import com.amol.waterbill.model.UserListModel;
import com.amol.waterbill.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends AppCompatActivity {
    private static final String TAG = "UserList";
    private static RecyclerView user_list_recyclerview;
    private static Button btnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        user_list_recyclerview = (RecyclerView) findViewById(R.id.user_list_recyclerview);
        user_list_recyclerview.setHasFixedSize(true);
        user_list_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        btnGoHome = (Button) findViewById(R.id.btnGoHome);
        userAPICall();
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void userAPICall() {
        Call<List<UserListModel>> call = RetrofitClient
                .getInstance().getApi().water_users();
        call.enqueue(new Callback<List<UserListModel>>() {
            @Override
            public void onResponse(Call<List<UserListModel>> call, Response<List<UserListModel>> response) {
                user_list_recyclerview.setAdapter(new UserListAdapter(response.body()));
                Log.d(TAG, "Success");
            }
            @Override
            public void onFailure(Call<List<UserListModel>> call, Throwable t) {
                Log.d(TAG, "Failure");
            }
        });
    }
}