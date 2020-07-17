package com.example.mymvvm.splach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.mymvvm.Profile.ProfileActivity;
import com.example.mymvvm.R;
import com.example.mymvvm.register.RegisterActivity;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        SharedPreferences prefs =getSharedPreferences("data", MODE_PRIVATE);
        String token=prefs.getString("token","0");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(token==null || token.equals("0")){
                    Intent intent=new Intent(SplachActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(SplachActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }

            }
        },3000);
    }
}
