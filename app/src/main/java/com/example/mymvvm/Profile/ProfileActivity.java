package com.example.mymvvm.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymvvm.R;
import com.example.mymvvm.login.LoginActivity;
import com.example.mymvvm.login.LoginViewModel;
import com.example.mymvvm.model.LoginModel;
import com.example.mymvvm.model.ProfileModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {


    @BindView(R.id.name_Profile)
    TextView nameProfile;
    @BindView(R.id.email_Profile)
    TextView emailProfile;

    ProfileViewModel profileViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        profileViewModel= ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.CallprofilenApi(ProfileActivity.this);
        profileViewModel.profileModelMutableLiveData.observe(this, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {

                nameProfile.setText(profileModel.getData().getName());
                emailProfile.setText(profileModel.getData().getEmail());


            }
        });


    }
}
