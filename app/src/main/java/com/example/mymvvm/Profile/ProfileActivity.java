package com.example.mymvvm.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymvvm.R;
import com.example.mymvvm.login.LoginActivity;
import com.example.mymvvm.model.LogoutModel;
import com.example.mymvvm.model.ProfileModel;
import com.example.mymvvm.updateProfile.UpdateProfileActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {


    @BindView(R.id.name_Profile)
    TextView nameProfile;
    @BindView(R.id.email_Profile)
    TextView emailProfile;

    ProfileViewModel profileViewModel;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.updater)
    Button updater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        intialize();


        updater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void intialize() {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.CallprofilenApi(ProfileActivity.this);
        profileViewModel.profileModelMutableLiveData.observe(this, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {

                nameProfile.setText(profileModel.getData().getName());
                emailProfile.setText(profileModel.getData().getEmail());


            }
        });

        profileViewModel.logoutModelMutableLiveData.observe(this, new Observer<LogoutModel>() {
            @Override
            public void onChanged(LogoutModel logoutModel) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("token", "0");
                editor.apply();
                Toast.makeText(ProfileActivity.this, logoutModel.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }

    @OnClick(R.id.logout)
    public void onViewClicked() {
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String token = prefs.getString("token", "0");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fcm_token", token);
        profileViewModel.CallLogOutnApi(ProfileActivity.this, hashMap);

    }
}
