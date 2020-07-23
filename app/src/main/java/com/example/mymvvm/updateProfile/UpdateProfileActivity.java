package com.example.mymvvm.updateProfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymvvm.Profile.ProfileActivity;
import com.example.mymvvm.Profile.ProfileViewModel;
import com.example.mymvvm.R;
import com.example.mymvvm.login.LoginActivity;
import com.example.mymvvm.model.ProfileModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends AppCompatActivity {

    @BindView(R.id.email_update)
    EditText emailUpdate;
    @BindView(R.id.password_update)
    EditText passwordUpdate;
    @BindView(R.id.updating)
    Button updating;
    ProfileViewModel profileViewModel;
    String email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.mutableLiveData.observe(this, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("token", profileModel.getData().getToken());
                editor.apply();

                Intent intent=new Intent(UpdateProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();



            }
        });

    }

    @OnClick(R.id.updating)
    public void onViewClicked() {


        email=emailUpdate.getText().toString();
        name = passwordUpdate.getText().toString();
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("name",name);
        hashMap.put("phone","4545");

        profileViewModel.CallUpdateProdileApi(UpdateProfileActivity.this,hashMap);


    }
}
