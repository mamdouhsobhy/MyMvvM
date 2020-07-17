package com.example.mymvvm.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mymvvm.Profile.ProfileActivity;
import com.example.mymvvm.R;
import com.example.mymvvm.model.LoginModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_login)
    EditText emailLogin;
    @BindView(R.id.password_login)
    EditText passwordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    String mEmail,mPassword;
    LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.loginModelMutableLiveData.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("token", loginModel.getData().getToken());
                editor.apply();
                Intent intent=new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mEmail=emailLogin.getText().toString();
        mPassword = passwordLogin.getText().toString();
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("email",mEmail);
        hashMap.put("password",mPassword);
        loginViewModel.CallloginApi(LoginActivity.this,hashMap);

    }
}
